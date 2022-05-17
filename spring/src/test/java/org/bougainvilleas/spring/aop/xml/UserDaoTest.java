package org.bougainvilleas.spring.aop.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * aop测试
 * @author renqiankun
 */
class UserDaoTest {

    @Test
    @DisplayName("基于xml aop")
    void testProxy(){
        ApplicationContext context=new ClassPathXmlApplicationContext("aop/xml/aop.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.add();
        userDao.update("1");
    }
}
