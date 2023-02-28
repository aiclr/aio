package org.bougainvilleas.spring.aop.annotation;

import org.bougainvilleas.spring.aop.annotation.AOPAnnotation.TypeAOP;
import org.springframework.stereotype.Component;

/**
 * 外AOP Annotation
 *
 * @author renqiankun
 */
@TypeAOP(12)
@Component
public class UserDao2 {


  public void add(Integer age) {
    System.out.println("add AOP Annotation");
  }

  public String update(String id) {
    System.out.println("update AOP Annotation");
    return id + "";
  }

  public void del(Integer age) {
    System.out.println("del AOP Annotation");
  }

}
