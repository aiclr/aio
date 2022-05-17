package org.bougainvilleas.spring.features;

import org.bougainvilleas.spring.ioc.annotation.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用JUnit4框架测试
 *
 * @author renqiankun
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ioc/annotation/annotation.xml"})
public class JTest4
{

    @Autowired
    UserService userService;

    @Test
    @DisplayName("使用JUnit4框架测试")
    public void testAdd(){
        System.out.println("Spring5+JUnit4");
//        ApplicationContext context=new ClassPathXmlApplicationContext("source/ioc/annotation/annotation.xml");
//        UserService userService=context.getBean("userService",UserService.class);
        userService.add();
    }

    @Test
    public void testXml(){
        System.out.println("Spring5+JUnit4");
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/annotation/annotation.xml");
        UserService userService2=context.getBean("userService",UserService.class);
        userService2.add();
    }
}
