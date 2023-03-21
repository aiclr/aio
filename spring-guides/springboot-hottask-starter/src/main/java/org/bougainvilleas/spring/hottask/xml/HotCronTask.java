package org.bougainvilleas.spring.hottask.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static org.springframework.scheduling.config.ScheduledTaskRegistrar.CRON_DISABLED;

@XmlRootElement(name = "HotCronTask")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotCronTask
{
    @Override
    public String toString()
    {
        return "HotCronTask{" +
                "expression='" + expression + '\'' +
                '}';
    }

    /**
     * cron 表达式
     */
    private String expression = CRON_DISABLED;

    public String getExpression()
    {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public HotCronTask()
    {
    }

    public HotCronTask(String expression)
    {
        this.expression = expression;
    }
}
