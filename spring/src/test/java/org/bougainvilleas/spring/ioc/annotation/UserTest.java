package org.bougainvilleas.spring.ioc.annotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * junit test
 * @author renqiankun
 */
class UserTest {

    @Test
    @DisplayName("注解 注入属性")
    void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/annotation/annotation.xml");
        User user = context.getBean("user",User.class);
        System.out.println(user);
    }
}
