package org.bougainvilleas.spring.hottask.xml;

public class HotFixedTask
{
    /**
     * 初始化后延时时间
     */
    private long initialDelay = -1L;

    public HotFixedTask()
    {
    }

    @Override
    public String toString()
    {
        return "HotFixedTask{" +
                "initialDelay=" + initialDelay +
                '}';
    }

    public HotFixedTask(long initialDelay)
    {
        this.initialDelay = initialDelay;
    }

    public long getInitialDelay()
    {
        return initialDelay;
    }

    public void setInitialDelay(long initialDelay)
    {
        this.initialDelay = initialDelay;
    }
}
