package org.bougainvilleas.spring.ioc.xml.autowire;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动装配
 * @author renqiankun
 */
class EmpTest {

    @Test
    @DisplayName("自动装配")
    void testAutowireByName(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/autowire/autowireByName.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }

    /**
     * 自动装配时 同一文件中不能定义相同类型的Bean
     */
    @Test
    @DisplayName("自动装配时 同一文件中不能定义相同类型的Bean")
    void testAutowireByType(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/autowire/autowireByType.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }


}
