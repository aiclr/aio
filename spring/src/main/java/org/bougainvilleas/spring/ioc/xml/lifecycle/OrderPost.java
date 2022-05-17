package org.bougainvilleas.spring.ioc.xml.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器
 * 默认会给 当前配置文件内的 所有bean 都加上这个后置处理器
 * @author renqiankun
 */
public class OrderPost implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化方法之前，后置处理器执行");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化方法之后，后置处理器执行");
        return bean;
    }
}
