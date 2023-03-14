package org.bougainvilleas.spring.hottask.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hottask")
public class HotTaskProperties
{
    public static String HOT4TASK ="Σ＞―(灬⁺д⁺灬)♡―――＞";

    /**
     * hottask.core_pool_size
     */
    private int corePoolSize = 16;

    private boolean enabled = false;

    private String file = "${user.home}/hottask/hotTasks.xml";

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    public int getCorePoolSize()
    {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize)
    {
        this.corePoolSize = corePoolSize;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
