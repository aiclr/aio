package org.bougainvilleas.base.grace.reflex;

/**
 * 不能设置属性 利用反射来赋予属性
 * @author renqiankun
 * 2022-06-17 09:55:11 星期五
 */
public class Duck extends Animal
{
    /**
     * 父类可访问 属性
     */
    private int level;

    public Duck(int level)
    {
        this.level = level;
    }

    private int getLevel()
    {
        return level;
    }
    private void setLevel(int level)
    {
        this.level=level;
    }

    @Override
    public String toString()
    {
        return super.toString()+"\tlevel=" + level;
    }
}
