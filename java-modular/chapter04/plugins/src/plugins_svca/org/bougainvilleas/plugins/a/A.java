package org.bougainvilleas.plugins.a;

import org.bougainvilleas.plugins.api.Api;

public class A implements Api
{
    @Override
    public void exec()
    {
        System.err.println("plugin-A");
    }
}
