package org.bougainvilleas.spring.hottask.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "HotTask")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskBody implements Serializable
{
    private static final long serialVersionUID = 1L;

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
     * cron 表达式 策略定时任务
     */
    @XmlElement(name = "HotCronTask")
    private HotCronTask cronTask;
    /**
     * 频率定时任务
     */
    @XmlElement(name = "HotFixedRateTask")
    private HotFixedRateTask fixedRateTask;
    /**
     * 延时定时任务
     */
    @XmlElement(name = "HotFixedDelayTask")
    private HotFixedDelayTask fixedDelayTask;

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

    public HotCronTask getCronTask()
    {
        return cronTask;
    }

    public void setCronTask(HotCronTask cronTask)
    {
        this.cronTask = cronTask;
    }

    public HotFixedRateTask getFixedRateTask()
    {
        return fixedRateTask;
    }

    public void setFixedRateTask(HotFixedRateTask fixedRateTask)
    {
        this.fixedRateTask = fixedRateTask;
    }

    public HotFixedDelayTask getFixedDelayTask()
    {
        return fixedDelayTask;
    }

    public void setFixedDelayTask(HotFixedDelayTask fixedDelayTask)
    {
        this.fixedDelayTask = fixedDelayTask;
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



    public TaskBody()
    {
    }

    public TaskBody(String id, String className, String beanName, String methodName, HotCronTask cronTask, HotFixedRateTask fixedRateTask, HotFixedDelayTask fixedDelayTask, long createTime, long updateTime, boolean open, boolean del)
    {
        this.id = id;
        this.className = className;
        this.beanName = beanName;
        this.methodName = methodName;
        this.cronTask = cronTask;
        this.fixedRateTask = fixedRateTask;
        this.fixedDelayTask = fixedDelayTask;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.open = open;
        this.del = del;
    }

    @Override
    public String toString()
    {
        return "TaskBody{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", cronTask=" + cronTask +
                ", fixedRateTask=" + fixedRateTask +
                ", fixedDelayTask=" + fixedDelayTask +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", open=" + open +
                ", del=" + del +
                '}';
    }
}
