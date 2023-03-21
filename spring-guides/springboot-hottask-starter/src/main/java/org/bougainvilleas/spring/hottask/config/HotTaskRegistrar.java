package org.bougainvilleas.spring.hottask.config;


import org.bougainvilleas.spring.hottask.utils.HotTaskUtils;
import org.bougainvilleas.spring.hottask.xml.TaskBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.FixedDelayTask;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

/**
 * 参考：org.springframework.scheduling.config.ScheduledTaskRegistrar
 *
 * @author caddy
 */
@Component
public class HotTaskRegistrar implements InitializingBean, DisposableBean
{
    private static final Logger log = LoggerFactory.getLogger(HotTaskRegistrar.class);

    private Map<Runnable, HotScheduledTask> unresolvedTasks = new ConcurrentHashMap<>(36);

    private Set<Runnable> hotTasks = new CopyOnWriteArraySet<>();

    private TaskScheduler taskScheduler;

    private ScheduledExecutorService localExecutor;

    public HotTaskRegistrar(ScheduledExecutorService localExecutor)
    {
        Assert.notNull(localExecutor, "ScheduledExecutorService must not be null");
        this.localExecutor = localExecutor;
        this.taskScheduler = new ConcurrentTaskScheduler(this.localExecutor);
    }

    /**
     * Calls {@link #scheduleTasks()} at bean construction time.
     */
    @Override
    public void afterPropertiesSet()
    {
        scheduleTasks();
    }

    /**
     * Schedule all registered tasks against the underlying
     */
    @SuppressWarnings("deprecation")
    protected void scheduleTasks()
    {
        if (this.taskScheduler == null)
        {
            log.error("{}启动时未初始化线程池，启用默认线程池配置", HotTaskProperties.HOT4TASK);
            localExecutor = Executors.newSingleThreadScheduledExecutor();
            taskScheduler = new ConcurrentTaskScheduler(localExecutor);
        }
    }

    @Override
    public void destroy()
    {
        log.warn("{}开始销毁热切换定时任务", HotTaskProperties.HOT4TASK);
        for (HotScheduledTask task : unresolvedTasks.values())
            task.cancel();
        if (localExecutor != null)
            localExecutor.shutdownNow();
        log.warn("{}销毁热切换定时任务完成", HotTaskProperties.HOT4TASK);
    }

    public synchronized void addTask(Task task)
    {
        if (task != null)
        {
            if (unresolvedTasks.containsKey(task.getRunnable()))
                rmTask(task);
            unresolvedTasks.put(task.getRunnable(), scheduleTask(task));
            hotTasks.add(task.getRunnable());
            log.info("{}装载定时任务 {},{},Runnable={}", HotTaskProperties.HOT4TASK, task.getClass().getSimpleName(), task, task.getRunnable());
        }
    }

    public synchronized void rmTask(Task task)
    {
        if (task != null)
        {
            HotScheduledTask scheduledTask = unresolvedTasks.remove(task.getRunnable());
            if (scheduledTask != null)
            {
                scheduledTask.cancel();
                hotTasks.remove(task.getRunnable());
                log.info("{}卸载定时任务 {},{},Runnable={}", HotTaskProperties.HOT4TASK, task.getClass().getSimpleName(), task, task.getRunnable());
            }
        }
    }

    @Nullable
    public HotScheduledTask scheduleTask(Task task)
    {
        HotScheduledTask scheduledTask = unresolvedTasks.remove(task.getRunnable());
        boolean newTask = false;
        if (scheduledTask == null)
        {
            scheduledTask = new HotScheduledTask(task);
            newTask = true;
        }

        if (taskScheduler != null)
        {
            if (task instanceof CronTask)
            {
                CronTask cronTask = (CronTask) task;
                scheduledTask.future = taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
            }
            if (task instanceof FixedRateTask)
            {
                FixedRateTask fixedRateTask = (FixedRateTask) task;
                if (fixedRateTask.getInitialDelay() > 0)
                {
                    Date startTime = new Date(taskScheduler.getClock().millis() + fixedRateTask.getInitialDelay());
                    scheduledTask.future = taskScheduler.scheduleAtFixedRate(fixedRateTask.getRunnable(), startTime, fixedRateTask.getInterval());
                }
                else
                    scheduledTask.future = taskScheduler.scheduleAtFixedRate(fixedRateTask.getRunnable(), fixedRateTask.getInterval());
            }
            if (task instanceof FixedDelayTask)
            {
                FixedDelayTask fixedDelayTask = (FixedDelayTask) task;
                Date startTime = new Date(taskScheduler.getClock().millis() + fixedDelayTask.getInitialDelay());
                scheduledTask.future = taskScheduler.scheduleWithFixedDelay(fixedDelayTask.getRunnable(), startTime, fixedDelayTask.getInterval());
            }
        }
        else
        {
            addTask(task);
            unresolvedTasks.put(task.getRunnable(), scheduledTask);
        }
        return (newTask ? scheduledTask : null);
    }



    //添加 task
    public Consumer<TaskBody> ADD_TASK = task ->
    {
        if (HotTaskUtils.OPEN.and(HotTaskUtils.DEL.negate()).test(task))
        {
            if (null != task.getCronTask())
            {
                addTask(HotTaskUtils.getCronTask.apply(task));
            }
            else if (null != task.getFixedRateTask())
            {
                addTask(HotTaskUtils.getFixedRateTask.apply(task));
            }
            else if (null != task.getFixedDelayTask())
            {
                addTask(HotTaskUtils.getFixedDelayTask.apply(task));
            }
            else
            {
                log.error("定时任务数据不合法请检测");
            }
        }
    };

    //移除 任务
    public Consumer<TaskBody> RM_TASK = task ->
    {
        if (null != task.getCronTask())
        {
            rmTask(HotTaskUtils.getCronTask.apply(task));
        }
        else if (null != task.getFixedRateTask())
        {
            rmTask(HotTaskUtils.getFixedRateTask.apply(task));
        }
        else if (null != task.getFixedDelayTask())
        {
            rmTask(HotTaskUtils.getFixedDelayTask.apply(task));
        }
        else
        {
            log.error("定时任务数据不合法请检测");
        }
    };

}
