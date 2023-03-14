package org.bougainvilleas.spring.hottask.xml;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "HotTasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLRoot
{

    @XmlElement(name = "HotTask")
    private Set<TaskBody> taskSet;

    /**
     * 定时任务的唯一标识 id 相当于 数据表主键
     * 当前任务集合中的 最大id 用于新增任务时设置id
     */
    @XmlAttribute(name = "id")
    private String id;

    @Override
    public String toString()
    {
        return "XMLRoot{" +
                "taskSet=" + taskSet +
                ", id='" + id + '\'' +
                '}';
    }

    public Set<TaskBody> getTaskSet()
    {
        return taskSet;
    }

    public void setTaskSet(Set<TaskBody> taskSet)
    {
        this.taskSet = taskSet;
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
