package org.bougainvilleas.ilg.study.chapter13;

/**
 * 限制:
 * ExpandoMetaClass 注入的方法 只能从 Groovy 代码内调用
 * 不能从编译过的java代码中调用
 * 不能从java代码中通过反射来使用
 */
public class CallDynamicMethod
{
    public static void main(String[] args)
    {
        groovy.lang.GroovyObject instance= new org.bougainvilleas.ilg.study.chapter13.User(0,1000,0);

        System.err.println(instance.getClass());

        final Object all = instance.invokeMethod("all", new Object[]{});
        System.err.println("all salary: "+ all);

        final Object isFemale = instance.invokeMethod("isFemale", new Object[]{});
        System.err.println("isFemale? "+ isFemale);
    }
}
