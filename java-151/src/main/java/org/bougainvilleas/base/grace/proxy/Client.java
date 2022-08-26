package org.bougainvilleas.base.grace.proxy;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.logging.*;

/**
 * 1607521710
 */
public class Client {

    private static Logger logger=Logger.getLogger("org.bougainvilleas.base.grace.proxy.client");

    private static SimpleFormatter simpleFormatter=new SimpleFormatter();

    public static void main(String[] args) {

        Handler fh;
        try {
            fh = new FileHandler("%t/wombat.log",true);
            fh.setFormatter(simpleFormatter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fh);
        logger.setLevel(Level.FINEST);

        logger.fine("简单环绕代理");
        Foo foo=(Foo)DebugProxy.newInstance(new FooImpl());
        foo.bar(null);

        /**
         * SEVERE (highest value)
         * WARNING
         * INFO
         * CONFIG
         * FINE
         * FINER
         * FINEST (lowest value)
         */
        logger.log(Level.SEVERE,"hashCode、equals、toString proxy");
        logger.log(Level.WARNING,"hashCode、equals、toString proxy");
        logger.log(Level.INFO,"hashCode、equals、toString proxy");
        logger.log(Level.CONFIG,"hashCode、equals、toString proxy");
        logger.log(Level.FINE,"hashCode、equals、toString proxy");
        logger.log(Level.FINER,"hashCode、equals、toString proxy");
        logger.log(Level.FINEST,"hashCode、equals、toString proxy");

        Class<?>[] proxyInterface = new Class[]{Foo.class};
        Foo foo2=(Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),proxyInterface,new Delegator(proxyInterface,new Object[]{new FooImpl()}));
        logger.fine(foo2.toString());
        logger.fine(String.valueOf(foo2.equals(foo)));
        logger.fine(String.valueOf(foo2.hashCode()));
        foo2.bar(null);
    }
}
