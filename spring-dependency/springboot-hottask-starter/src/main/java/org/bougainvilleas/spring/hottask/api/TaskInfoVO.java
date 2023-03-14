package org.bougainvilleas.spring.hottask.api;

import static org.springframework.scheduling.config.ScheduledTaskRegistrar.CRON_DISABLED;

public class TaskInfoVO
{
    /**
     * 定时任务的唯一标识 id 相当于 数据表主键
     */
    private String id;
    /**
     * 全类名
     */
    private String className;
    /**
     * 定时任务实例对象名
     */
    private String beanName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * cron 表达式
     */
    private String expression = CRON_DISABLED;
    /**
     * 初始化后延时时间
     */
    private long initialDelay = -1L;
    /**
     * FixedRateTask
     */
    private long interval = -1L;
    /**
     * FixedDelayTask
     */
    private long delay = -1L;
    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 更新时间
     */
    private long updateTime;
    /**
     * 任务是否开启
     */
    private boolean open;
    /**
     * 是否失效数据
     */
    private boolean del;

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public boolean isDel()
    {
        return del;
    }

    public void setDel(boolean del)
    {
        this.del = del;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getBeanName()
    {
        return beanName;
    }

    public void setBeanName(String beanName)
    {
        this.beanName = beanName;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getExpression()
    {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public long getInitialDelay()
    {
        return initialDelay;
    }

    public void setInitialDelay(long initialDelay)
    {
        this.initialDelay = initialDelay;
    }

    public long getInterval()
    {
        return interval;
    }

    public void setInterval(long interval)
    {
        this.interval = interval;
    }

    public long getDelay()
    {
        return delay;
    }

    public void setDelay(long delay)
    {
        this.delay = delay;
    }

    public long getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(long createTime)
    {
        this.createTime = createTime;
    }

    public long getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(long updateTime)
    {
        this.updateTime = updateTime;
    }

    public TaskInfoVO()
    {
    }
}
