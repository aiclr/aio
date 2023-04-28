package org.bougainvilleas.base.grace.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author renqiankun
 * 2022-06-17 11:48:04 星期五
 */
public class Client
{
    public static void main(String[] args)
    {
        Business business = new Business();
        Class<?> businessClass = Business.class;
        final Method[] methods = businessClass.getMethods();

        List<Method> before = new ArrayList<>();
        List<Method> core = new ArrayList<>();
        List<Method> after = new ArrayList<>();
        Arrays.stream(methods)
                .forEach(method ->
                {
                    if (method.isAnnotationPresent(MyBefore.class))
                    {
                        before.add(method);
                    }
                    if (method.isAnnotationPresent(MyCore.class))
                    {
                        core.add(method);
                    }
                    if (method.isAnnotationPresent(MyAfter.class))
                    {
                        after.add(method);
                    }
                });

        core.forEach(method ->
        {
            try
            {
                for (Method bm : before)
                    bm.invoke(business);
                method.invoke(business);
                for (Method af : after)
                    af.invoke(business);
            } catch (IllegalAccessException | InvocationTargetException e)
            {
                e.printStackTrace();
            }
        });
    }
}
