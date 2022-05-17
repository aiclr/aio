package org.bougainvilleas.spring.ioc.xml.collection;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 集合注入
 * @author renqiankun
 */
class StuTest {

    @Test
    @DisplayName("集合注入")
    void testShow(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/collection/stuBean.xml");
        Stu stu=context.getBean("stu", Stu.class);
        stu.show();
    }
}
