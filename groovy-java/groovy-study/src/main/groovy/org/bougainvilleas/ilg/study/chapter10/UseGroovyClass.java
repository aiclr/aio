package org.bougainvilleas.ilg.study.chapter10;

/**
 * 在Java中创建于传递Groovy闭包
 *
 * Groovy调用闭包时，只是使用了一个名为call() 的特殊方法
 * 在java中创建闭包，只需要一个包含 call()方法的类
 * 如果groovy 要向闭包传递实参如下的 2 ，必须确保call() 方法接受这些实参作为call()方法的形参
 *
 */
public class UseGroovyClass
{
    public static void main(String[] args)
    {
        AGroovyClass instance = new AGroovyClass();
        Object result = instance.useClosure(new Object()
        {
            public String call()
            {
                return "You called from groovy!";
            }
        });
        System.err.println("Received: " + result);

        System.err.println("Received: " +
                instance.passToClosure(2, new Object()
                {
                    public String call(int value)
                    {
                        return "You called from Groovy with value " + value;
                    }
                }));


    }
}
