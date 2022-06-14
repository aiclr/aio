package org.bougainvilleas.spring.aop.annotation;

import org.bougainvilleas.spring.aop.annotation.AOPAnnotation.PK;
import org.springframework.stereotype.Component;

/**
 * 外AOP Annotation
 * @author renqiankun
 */
@Component
public class UserDao {



    public void add(int age) {
        System.out.println("AOP Annotation");
    }

    public String update(String id) {
//        int a=10/0;

        System.out.println("AOP Annotation");
        return id;
    }

    @PK(12)
    public void del(int age) {
        System.out.println("AOP Annotation");
    }

}
