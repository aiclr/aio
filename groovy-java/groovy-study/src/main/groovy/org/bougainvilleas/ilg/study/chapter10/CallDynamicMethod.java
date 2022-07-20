package org.bougainvilleas.ilg.study.chapter10;

/**
 * 如果Groovy 因为某些原因无法执行被调用的方法，
 * 或者该方法没有执行成功，
 * 调用invokeMethod() 将会失败。需要处理该方法可能抛出的异常
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
    }
}
