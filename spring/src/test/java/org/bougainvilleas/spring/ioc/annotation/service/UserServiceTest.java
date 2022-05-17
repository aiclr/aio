package org.bougainvilleas.spring.ioc.annotation.service;

import org.bougainvilleas.spring.ioc.annotation.config.SpringConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author renqiankun
 */
class UserServiceTest {

    /**
     * 引入context名称空间，扫描注解
     */
    @DisplayName("引入context名称空间，扫描注解")
    @Test
    void testAdd(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/annotation/annotation.xml");
        UserService userService=context.getBean("userService",UserService.class);
        userService.add();
    }

    /**
     * 完全基于注解，扫描注解 等价于springboot
     */
    @DisplayName("完全基于注解，扫描注解 等价于springboot")
    @Test
    void test(){
        /**
         * 加载配置类
         */
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService=context.getBean("userService",UserService.class);
        userService.add();
    }
}
