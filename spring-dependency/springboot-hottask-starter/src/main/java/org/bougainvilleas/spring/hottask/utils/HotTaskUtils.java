package org.bougainvilleas.spring.hottask.utils;

import org.bougainvilleas.spring.hottask.api.TaskInfoVO;
import org.bougainvilleas.spring.hottask.config.HotTask;
import org.bougainvilleas.spring.hottask.xml.HotCronTask;
import org.bougainvilleas.spring.hottask.xml.HotFixedDelayTask;
import org.bougainvilleas.spring.hottask.xml.HotFixedRateTask;
import org.bougainvilleas.spring.hottask.xml.TaskBody;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.FixedDelayTask;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.springframework.scheduling.config.ScheduledTaskRegistrar.CRON_DISABLED;

@Component
public class HotTaskUtils implements ApplicationContextAware
{
    private static final ZoneId zone = ZoneId.of("Asia/Shanghai");

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        HotTaskUtils.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        return context;
    }

    public static <T> T getBean(String beanName, Class<T> cls)
    {
        return context.getBean(beanName, cls);
    }

    public static Object getBean(String beanName)
    {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> cls)
    {
        return context.getBean(cls);
    }

    public static <T> T getProperty(String property, Class<T> tClass)
    {
        return context.getEnvironment().getProperty(property, tClass);
    }


    //任务开启
    public static Predicate<TaskBody> OPEN = TaskBody::isOpen;
    //任务删除
    public static Predicate<TaskBody> DEL = TaskBody::isDel;

    //任务已加载
    public static boolean REGISTERED(TaskBody task1, TaskBody task2)
    {
        if (task1 != null && task2 != null)
        {
            if (STR_EQUALS.test(task1.getClassName(), task2.getClassName()))
            {
                if (STR_EQUALS.test(task1.getBeanName(), task2.getBeanName()))
                {
                    if (STR_EQUALS.test(task1.getMethodName(), task2.getMethodName()))
                    {
                        if (DEL.negate().test(task1) && DEL.negate().test(task2))
                        {
                            return OPEN.test(task1) && OPEN.test(task2);
                        }
                    }
                }

            }
        }
        return false;
    }

    public static BiPredicate<String, String> STR_EQUALS = (str1, str2) ->
    {
        if (StringUtils.hasText(str1) && StringUtils.hasText(str1))
        {
            return str1.equals(str2);
        }
        return false;
    };

    //create HotTask
    public static Function<TaskBody, TaskInfoVO> po2vo = po ->
    {
        final TaskInfoVO vo = new TaskInfoVO();
        if (null != po)
        {
            vo.setId(po.getId());
            vo.setClassName(po.getClassName());
            vo.setBeanName(po.getBeanName());
            vo.setMethodName(po.getMethodName());
            vo.setCreateTime(po.getCreateTime());
            vo.setUpdateTime(po.getUpdateTime());
            vo.setDel(po.isDel());
            vo.setOpen(po.isOpen());
            if (null != po.getCronTask())
            {
                vo.setExpression(po.getCronTask().getExpression());
            }
            else if (null != po.getFixedRateTask())
            {
                vo.setInitialDelay(po.getFixedRateTask().getInitialDelay());
                vo.setInterval(po.getFixedRateTask().getInterval());
            }
            else if (null != po.getFixedDelayTask())
            {
                vo.setInitialDelay(po.getFixedDelayTask().getInitialDelay());
                vo.setDelay(po.getFixedDelayTask().getDelay());
            }
        }
        return vo;
    };
    public static Function<TaskInfoVO, TaskBody> vo2po = vo ->
    {
        final TaskBody po = new TaskBody();
        if (null != vo)
        {
            po.setId(vo.getId());
            po.setClassName(vo.getClassName());
            po.setBeanName(vo.getBeanName());
            po.setMethodName(vo.getMethodName());
            po.setCreateTime(vo.getCreateTime());
            po.setUpdateTime(vo.getUpdateTime());
            po.setDel(vo.isDel());
            po.setOpen(vo.isOpen());
            if (StringUtils.hasText(vo.getExpression()) && !CRON_DISABLED.equals(vo.getExpression()))
            {
                po.setCronTask(new HotCronTask(vo.getExpression()));
            }
            else if (vo.getDelay() > 0L)
            {
                po.setFixedDelayTask(new HotFixedDelayTask(Math.max(vo.getInitialDelay(), 0L),vo.getDelay()));
            }
            else if (vo.getInterval() > 0L)
            {
                po.setFixedRateTask(new HotFixedRateTask(Math.max(vo.getInitialDelay(), 0L),vo.getDelay()));
            }
            else
                throw new IllegalArgumentException("任务参数异常请检查");
        }
        else
            throw new IllegalArgumentException("任务参数异常请检查");
        return po;
    };


    public static Function<TaskBody, HotTask> CREATE_TASK = task -> new HotTask(task.getBeanName(), task.getMethodName());

    public static Function<TaskBody, CronTask> getCronTask = taskBody ->
            new CronTask(CREATE_TASK.apply(taskBody), new CronTrigger(taskBody.getCronTask().getExpression(), zone));

    public static Function<TaskBody, FixedRateTask> getFixedRateTask = taskBody ->
            new FixedRateTask(CREATE_TASK.apply(taskBody), taskBody.getFixedRateTask().getInterval(), Math.max(taskBody.getFixedRateTask().getInitialDelay(), 0L));

    public static Function<TaskBody, FixedDelayTask> getFixedDelayTask = taskBody ->
            new FixedDelayTask(CREATE_TASK.apply(taskBody), taskBody.getFixedDelayTask().getDelay(), Math.max(taskBody.getFixedDelayTask().getInitialDelay(), 0L));


    //是 CronTask
    public static Predicate<TaskBody> CRON = taskBody -> null != taskBody.getCronTask()
            && CRON_DISABLED.equals(taskBody.getCronTask().getExpression());
    //FixedRateTask
    public static Predicate<TaskBody> RATE = taskBody -> null != taskBody.getFixedRateTask()
            && taskBody.getFixedRateTask().getInterval() > 0L;
    //FixedDelayTask
    public static Predicate<TaskBody> DELAY = taskBody -> null != taskBody.getFixedDelayTask()
            && taskBody.getFixedRateTask().getInterval() > 0L;


}
