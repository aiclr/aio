package org.bougainvilleas.ilg.study.chapter13;

import org.bougainvilleas.ilg.study.chapter10.DynamicGroovyClass;

import java.util.Calendar;

/**
 * @author renqiankun
 * 2022-07-25 18:25:12 星期一
 */
public class CallDynamicMethod
{
    public static void main(String[] args)
    {
        groovy.lang.GroovyObject instance=new DynamicGroovyClass();

        Object result1=instance.invokeMethod("squeak",new Object[]{});
        System.err.println("Received: "+result1);

        Object result2=instance.invokeMethod("quack",new Object[]{"like","a","duck"});
        System.err.println("Received: "+result2);

        groovy.lang.GroovyObject instance2 = new UsingEMCDSL();
        instance2.invokeMethod("daysFirNow",new Object[]{Calendar.getInstance()});

    }
}
