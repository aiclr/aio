package org.bougainvilleas.base.grace.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class DebugProxy implements InvocationHandler {
    private static Logger logger=Logger.getLogger("org.bougainvilleas.base.grace.proxy.DebugProxy");
    private static Formatter formatter=new MyLogFormatter();

    static {
        Handler fh;
        try {
            fh = new FileHandler("%t/wombat.log",true);
            fh.setFormatter(formatter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fh);
    }

    private Object obj;

    public static Object newInstance(Object obj){
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new DebugProxy(obj)
        );
    }

    public DebugProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try{
            logger.severe("before method "+ method.getName());
            result= method.invoke(obj,args);
        }catch (InvocationTargetException e)
        {
            throw e.getTargetException();
        }catch (Exception e){
            throw new RuntimeException("Unexpected invocation exception: "+e.getMessage());
        }finally {
            logger.severe("after method "+ method.getName());
        }

        return result;
    }
}
