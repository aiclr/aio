package org.bougainvilleas.spring.ioc.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * create Plain Ordinary Java Object
 *
 * @author renqiankun
 */
@Component
public class User {

    @Value("李二狗-注解")
    private String name;
    @Value("shou-注解")
    private String hand;
    @Value("book-注解")
    private String book;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", hand='" + hand + '\'' +
                ", book='" + book + '\'' +
                '}';
    }
}