package org.bougainvilleas.spring.ioc.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * junit test
 * @author renqiankun
 */
@DisplayName("UserTest - 加载spring xml 配置文件创建Bean")
class UserTest {

    @DisplayName("创建Bean")
    @Test
    void testAdd(){
        //1.加载spring配置文件，使用ApplicationContext时，加载配置文件时候就会创建Bean
        //ClassPathXmlApplicationContext 项目路径
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/xml/userBean.xml");
        //FileSystemXmlApplicationContext 绝对路径
//        ApplicationContext context = new FileSystemXmlApplicationContext("D:\\develop\\java\\spring5\\src\\source\\ioc\\userServiceBean.xml");

//使用BeanFactory时，context.getBean("user",User.class);时才创建对象
//        BeanFactory context = new ClassPathXmlApplicationContext("ioc/userServiceBean.xml");


        //2.获取配置创建的对象，当使用BeanFactory时，此时才创建Bean对象
        User user = context.getBean("user",User.class);
        user.add();
    }

    /**
     * set注入属性简化写法
     */
    @DisplayName("set注入属性简化写法")
    @Test
    void testUserBean1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/xml/userBean1.xml");
        User user = context.getBean("user",User.class);
        user.add();
    }

    /**
     * 注入空值
     *
     */
    @DisplayName("注入空值")
    @Test
    void testShow(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/xml/userBean.xml");
        User user = context.getBean("user",User.class);
        user.show();
    }

    /**
     * 注入空特殊字符
     */
    @DisplayName("注入空特殊字符")
    @Test
    void testShow1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/xml/userBean.xml");
        User user = context.getBean("user",User.class);
        user.show1();
    }
}
