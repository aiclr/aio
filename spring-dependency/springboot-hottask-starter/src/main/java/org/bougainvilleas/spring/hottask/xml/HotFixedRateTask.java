package org.bougainvilleas.spring.hottask.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotFixedRateTask")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotFixedRateTask extends HotFixedTask
{
    @Override
    public String toString()
    {
        return "HotFixedRateTask{" +
                "interval=" + interval +
                '}';
    }

    /**
     * FixedRateTask
     */
    private long interval = -1L;

    public HotFixedRateTask()
    {
    }

    public HotFixedRateTask(long interval)
    {
        this.interval = interval;
    }

    public HotFixedRateTask(long initialDelay, long interval)
    {
        super(initialDelay);
        this.interval = interval;
    }

    public long getInterval()
    {
        return interval;
    }

    public void setInterval(long interval)
    {
        this.interval = interval;
    }
}
