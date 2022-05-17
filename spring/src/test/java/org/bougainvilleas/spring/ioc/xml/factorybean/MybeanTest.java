package org.bougainvilleas.spring.ioc.xml.factorybean;


import org.bougainvilleas.spring.ioc.xml.collection.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 工厂bean 使用工厂创建bean
 * @author renqiankun
 */
@DisplayName("工厂bean 使用工厂创建bean")
class MybeanTest {


    @Test
    void testMybean(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/factorybean/factoryBean.xml");
        Course myBean = context.getBean("myBean", Course.class);
        System.err.println(myBean);
    }
}
