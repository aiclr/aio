package org.bougainvilleas.spring.ioc.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Emptest {

    /**
     * 注入内部属性
     */
    @Test
    @DisplayName("注入内部属性")
    void testShow(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/empBean.xml");
        Emp emp = context.getBean("emp", Emp.class);
        emp.show();
    }

    /**
     * 注入内部属性另一种写法，需要get方法，获取到内部bean，然后赋值改变内部bean属性
     */
    @Test
    @DisplayName("注入内部属性另一种写法，需要get方法，获取到内部bean，然后赋值改变内部bean属性")
    void testShow1(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/empBean1.xml");
        Emp emp = context.getBean("emp", Emp.class);
        emp.show();
    }

}
