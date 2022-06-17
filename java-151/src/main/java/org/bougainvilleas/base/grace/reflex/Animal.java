package org.bougainvilleas.base.grace.reflex;

/**
 * @author renqiankun
 * 2022-06-17 09:54:52 星期五
 */
public class Animal
{
    /**
     * 子类可访问 属性
     */
    private String name;

    @Override
    public String toString()
    {
        return "name=" + name;
    }
}
