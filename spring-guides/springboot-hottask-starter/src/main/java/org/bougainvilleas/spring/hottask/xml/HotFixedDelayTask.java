package org.bougainvilleas.spring.hottask.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotFixedDelayTask")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotFixedDelayTask extends HotFixedTask
{
    @Override
    public String toString()
    {
        return "HotFixedDelayTask{" +
                "delay=" + delay +
                '}';
    }

    /**
     * FixedDelayTask
     */
    private long delay = -1L;

    public HotFixedDelayTask()
    {
    }

    public HotFixedDelayTask(long delay)
    {
        this.delay = delay;
    }

    public HotFixedDelayTask(long initialDelay, long delay)
    {
        super(initialDelay);
        this.delay = delay;
    }

    public long getDelay()
    {
        return delay;
    }

    public void setDelay(long delay)
    {
        this.delay = delay;
    }
}
