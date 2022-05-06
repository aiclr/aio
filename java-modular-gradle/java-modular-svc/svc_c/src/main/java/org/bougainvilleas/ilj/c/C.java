package org.bougainvilleas.ilj.c;

import org.bougainvilleas.ilj.api.Api;
import org.bougainvilleas.ilj.api.SVC;

@SVC("C")
public class C implements Api
{
    @Override
    public void exec()
    {
        System.err.println("i am your father C");
    }
}
