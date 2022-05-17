package org.bougainvilleas.spring.ioc.xml.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author renqiankun
 */
class UserServiceTest {

    @Test
    void testAdd(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/service/userServiceBean.xml");
        UserService userService=context.getBean("userService",UserService.class);
        userService.add();
    }
}
