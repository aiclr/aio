package org.bougainvilleas.plugins.b;

import org.bougainvilleas.plugins.api.Api;

public class B implements Api
{
    @Override
    public void exec()
    {
        System.err.println("plugin-B");
    }
}
