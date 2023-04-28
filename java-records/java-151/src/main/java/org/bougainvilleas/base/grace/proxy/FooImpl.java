package org.bougainvilleas.base.grace.proxy;

import java.io.IOException;
import java.util.logging.*;

public class FooImpl implements Foo {
    private static Logger logger=Logger.getLogger("org.bougainvilleas.base.grace.proxy.FooImpl");
    private static Formatter formatter=new SimpleFormatter();

    static {
        Handler fh;
        try {
            fh = new FileHandler("%t/wombat2.log",true);
            fh.setFormatter(formatter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fh);
    }
    @Override
    public Object bar(Object data) {
        logger.info("FooImpl#run");
        return "2333";
    }
}
