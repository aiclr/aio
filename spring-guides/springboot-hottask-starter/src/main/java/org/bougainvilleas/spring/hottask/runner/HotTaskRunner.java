package org.bougainvilleas.spring.hottask.runner;

import org.bougainvilleas.spring.hottask.api.HotTaskSVC;
import org.bougainvilleas.spring.hottask.config.HotTaskRegistrar;
import org.bougainvilleas.spring.hottask.xml.XMLRoot;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Optional;

@Component
public class HotTaskRunner implements ApplicationRunner
{
    private final HotTaskRegistrar hotTaskRegistrar;
    private final HotTaskSVC hotTaskSVC;

    public HotTaskRunner(HotTaskRegistrar hotTaskRegistrar, HotTaskSVC hotTaskSVC)
    {
        Assert.notNull(hotTaskRegistrar, "HotTaskRegistrar must not be null");
        this.hotTaskRegistrar = hotTaskRegistrar;
        Assert.notNull(hotTaskSVC, "HotTaskSVC must not be null");
        this.hotTaskSVC = hotTaskSVC;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        Optional.ofNullable(hotTaskSVC.getAll())
                .map(XMLRoot::getTaskSet)
                .orElse(Collections.emptySet())
                .forEach(hotTaskRegistrar.ADD_TASK);
    }

}
