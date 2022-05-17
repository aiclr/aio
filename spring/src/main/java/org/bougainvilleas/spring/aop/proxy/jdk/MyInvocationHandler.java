package org.bougainvilleas.spring.aop.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 代理对像
 * @author renqiankun
 */
public class MyInvocationHandler implements InvocationHandler
{
    //把被代理对象传入代理对象
    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj=obj;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 被代理方法
     * @param args 被代理方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法前
        System.out.println("方法前");
        System.out.println("被代理方法名"+method.getName());
        System.out.println("被代理参数"+ Arrays.toString(args));

        if (method.getName().equals("add")){
            int a= (int) args[0];
            int b= (int) args[1];

            args[0]=a*10;
            args[1]=b*10;
        }
        if(method.getName().equals("update")){
            String str=(String)args[0];
            args[0]="----"+str+"----";
        }

        //被增强方法执行
        Object invoke = method.invoke(obj, args);

        //方法后
        System.out.println("方法后执行");
        System.out.println(obj);
        System.out.println("被代理方法名"+method.getName());
        System.out.println("被代理参数"+Arrays.toString(args));

        //被代理方法返回值
        return invoke;
    }
}
