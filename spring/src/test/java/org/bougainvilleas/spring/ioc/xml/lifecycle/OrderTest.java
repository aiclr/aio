package org.bougainvilleas.spring.ioc.xml.lifecycle;

import org.bougainvilleas.spring.ioc.xml.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 生命周期
 * @author renqiankun
 */
class OrderTest {

    @Test
    @DisplayName("bean 生命周期")
    void testLifeCycle(){
        //ApplicationContext没有close入口
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/lifecycle/order.xml");
        System.out.println("第四步：获取创建的bean");
        Order order = context.getBean("order", Order.class);
        System.out.println(order);
        //默认将当前配置文件内的所有bean都配置一个后置处理器
        Book book=context.getBean("book", Book.class);
        System.out.println(book);
        //手动关闭ioc容器
        context.close();

    }
}
