package org.bougainvilleas.ilj.a;

import org.bougainvilleas.ilj.api.Api;
import org.bougainvilleas.ilj.api.SVC;

@SVC("A")
public class A implements Api
{
    @Override
    public void exec()
    {
        System.err.println("i am your father A");
    }
}
