package org.bougainvilleas.spring.aop.annotation;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * aop测试
 * @author renqiankun
 */
class UserDaoTest {

    @Test
    @DisplayName("基于xml aop 切点参数")
    void testProxy(){
        ApplicationContext context=new ClassPathXmlApplicationContext("aop/annotation/annotation.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.add(20);
        userDao.update("1");
    }

    /**
     * 完全基于注解
     */
    @Test
    @DisplayName("完全基于注解 切点参数")
    void testProxy2(){
        ApplicationContext context=new AnnotationConfigApplicationContext(AOPConfig.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);
        System.out.println("完全基于注解");

        userDao.add(20);
        userDao.update("1");
    }


    /**
     * 获取切点上的注解
     */
    @Test
    @DisplayName("获取切点上的注解")
    void testProxy3(){
        ApplicationContext context=new AnnotationConfigApplicationContext(AOPConfig.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);
        System.out.println("获取切点上的注解");

        userDao.del(20);
    }

    /**
     * 类注解
     */
    @Test
    @DisplayName("类注解切面")
    void testProxy4(){
        ApplicationContext context=new AnnotationConfigApplicationContext(AOPConfig.class);
        UserDao2 userDao = context.getBean("userDao2", UserDao2.class);
        System.out.println("获取切点上的注解");
        userDao.add(20);
        userDao.update("20");
        userDao.del(20);
    }


}
