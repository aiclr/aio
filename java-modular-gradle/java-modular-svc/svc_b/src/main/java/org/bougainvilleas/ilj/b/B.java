package org.bougainvilleas.ilj.b;

import org.bougainvilleas.ilj.api.Api;
import org.bougainvilleas.ilj.api.SVC;

@SVC("B")
public class B implements Api
{
    @Override
    public void exec()
    {
        System.err.println("i am your father B");
    }
}
