package org.bougainvilleas.base.grace.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author renqiankun
 * 2022-06-17 10:00:04 星期五
 */
public class Client
{
    public static void main(String[] args)
    {
        Duck duck=new Duck(100);
        System.err.println(duck);

        //获取 大 Class 对象
        final Class<?> duckClass=Duck.class;

        //获取父类 大 Class 对象
        final Class<?> animalClass = duckClass.getSuperclass();

        //获取 private void setName(String name) 私有方法
        try
        {
            final Method setLevelMethod = duckClass.getDeclaredMethod("setLevel", int.class);
            //设置私有方法可访问
            setLevelMethod.setAccessible(true);
            //调用 实例 duck 的 private void setName(String name)
            System.err.println("反射执行duck实例私有方法setLevel");
            setLevelMethod.invoke(duck,121);
            System.err.println(duck);

            //获取父类 Animal 的属性 name
            final Field nameField = animalClass.getDeclaredField("name");
            //设置 protected 可访问
            nameField.setAccessible(true);
            //设置 name 值
            System.err.println("反射设置duck实例的父类才有的私有属性name");
            nameField.set(duck,"可达鸭");
            System.err.println("反射获取父类私有属性name="+nameField.get(duck));
            System.err.println(duck);


            /**
             * 通过 大 Class对象 创建实例
             */
            Duck duck1 = (Duck)duckClass.newInstance();
            System.err.println(duck1);
            //获取 Duck 的 参数为int 的构造器
            final Constructor<?> constructor = duckClass.getConstructor(int.class);
            Duck duck2 = (Duck)constructor.newInstance(12);
            System.err.println(duck2);


        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException | InstantiationException e)
        {
            e.printStackTrace();
        }

    }
}
