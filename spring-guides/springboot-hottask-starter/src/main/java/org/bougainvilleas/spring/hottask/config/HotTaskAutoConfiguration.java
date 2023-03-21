package org.bougainvilleas.spring.hottask.config;

import org.bougainvilleas.spring.hottask.api.HotTaskApi;
import org.bougainvilleas.spring.hottask.api.HotTaskSVCImpl;
import org.bougainvilleas.spring.hottask.runner.HotTaskRunner;
import org.bougainvilleas.spring.hottask.utils.HotTaskUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author renqiankun
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({HotTaskProperties.class})
@ConditionalOnProperty(prefix = "hottask", value = "enabled", havingValue = "true", matchIfMissing = false)
@Import({HotTaskRegistrar.class, HotTaskUtils.class, HotTaskSVCImpl.class, HotTaskRunner.class, HotTaskApi.class})
public class HotTaskAutoConfiguration
{
    private static final Logger log = LoggerFactory.getLogger(HotTaskAutoConfiguration.class);

    HotTaskProperties hotTaskProperties;

    public HotTaskAutoConfiguration(HotTaskProperties hotTaskProperties)
    {
        this.hotTaskProperties=hotTaskProperties;
    }

    @Bean
    public ScheduledExecutorService localExecutor()
    {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(hotTaskProperties.getCorePoolSize(),
                new ScheduledThreadFactory(),
                new ScheduledRejectExecutionHandler());
        //当一个提交的任务在运行前被取消，执行将被禁止
        //removeOnCancel=false (默认) 取消的任务不会自动从工作队列中移除，直到它的延时时间过期，可能导致取消任务被无限保留
        //removeOnCancel=true 调用setRemoveOnCancelPolicy(true) 任务一旦被取消将立即移除
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
        return scheduledThreadPoolExecutor;
    }

    static class ScheduledThreadFactory implements ThreadFactory
    {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        ScheduledThreadFactory()
        {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "TASK4HOT-" + poolNumber.getAndIncrement();
        }

        public Thread newThread(Runnable r)
        {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class ScheduledRejectExecutionHandler implements RejectedExecutionHandler
    {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor)
        {
            log.error("{} is rejected ######### completed task count is {}",runnable,executor.getCompletedTaskCount());
        }
    }
}
