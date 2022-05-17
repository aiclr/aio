package org.bougainvilleas.spring.ioc.xml.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * bean 生命周期
 * @author renqiankun
 */
public class Order {

    private static final Logger log = LoggerFactory.getLogger(Order.class);

    private String name;

    /**
     * 无参构造器
     */
    public Order() {
        log.error("第一步：执行无参构造器，创建bean");
        System.out.println("第一步：执行无参构造器，创建bean");
    }

    public void setName(String name) {
        this.name = name;
        log.error("第二步：注入属性");
        System.out.println("第二步：注入属性");
    }

    /**
     * 初始化方法
     * 需要在xml配置才会执行
     */
    public void initMethod(){
        log.error("第三步：调用初始化方法");
        System.out.println("第三步：调用初始化方法");
    }

    /**
     * ioc容器关闭，销毁bean
     */
    public void destroyMethod(){
        log.error("最后一步：ioc容器关闭，调用bean销毁方法");
        System.out.println("最后一步：ioc容器关闭，调用bean销毁方法");
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                '}';
    }
}
