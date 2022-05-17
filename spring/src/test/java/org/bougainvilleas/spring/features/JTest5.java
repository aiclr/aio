package org.bougainvilleas.spring.features;

import org.bougainvilleas.spring.ioc.annotation.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * 使用JUnit5框架测试
 *
 * @author renqiankun
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath:ioc/annotation/annotation.xml"})

//复合注解替换上面两个注解
@SpringJUnitConfig(locations = {"classpath:ioc/annotation/annotation.xml"})
class JTest5
{


    @Autowired
    UserService userService;

    @Test
    @DisplayName("Spring5+JUnit5+annotation")
    void testAnnotation(){
        System.out.println("Spring5+JUnit5");
        userService.add();
    }

    @Test
    @DisplayName("Spring5+JUnit5+xml")
    void testXml(){
        System.out.println("Spring5+JUnit5");
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/annotation/annotation.xml");
        UserService userService2=context.getBean("userService",UserService.class);
        userService2.add();
    }
}
