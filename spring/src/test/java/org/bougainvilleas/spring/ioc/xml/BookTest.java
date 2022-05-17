package org.bougainvilleas.spring.ioc.xml;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author renqiankun
 */
class BookTest {

    @Test
    void testShow(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/bookBean.xml");
        Book book=context.getBean("book", Book.class);
        book.show();
    }

    /**
     * bean作用域
     * 单例 多例
     */
    @Test
    @DisplayName("bean作用域: singleton-单例 prototype-多例")
    void testScop(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ioc/xml/bookBean.xml");
        Book book=context.getBean("book", Book.class);
        Book book1=context.getBean("book", Book.class);
        Assertions.assertEquals(book,book1);
    }
}
