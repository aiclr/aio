package org.bougainvilleas.base.grace.annotation;

/**
 * @author renqiankun
 * 2022-06-17 11:46:07 星期五
 */
public class Business
{
    @MyBefore
    public void init()
    {
        System.err.println("初始化...");
    }

    @MyCore
    public void core()
    {
        System.err.println("核心方法...");
    }

    @MyAfter
    public void destroy()
    {
        System.err.println("销毁...");
    }
}
