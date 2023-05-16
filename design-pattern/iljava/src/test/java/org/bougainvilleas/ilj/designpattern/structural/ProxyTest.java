package org.bougainvilleas.ilj.designpattern.structural;

import org.bougainvilleas.ilj.designpattern.structural.proxy.proxydynamic.ProxyFactory;
import org.bougainvilleas.ilj.designpattern.structural.proxy.proxydynamic.Student;
import org.bougainvilleas.ilj.designpattern.structural.proxy.proxydynamic.StudentImpl;
import org.bougainvilleas.ilj.designpattern.structural.proxy.proxystatic.Teacher;
import org.bougainvilleas.ilj.designpattern.structural.proxy.proxystatic.TeacherImpl;
import org.bougainvilleas.ilj.designpattern.structural.proxy.proxystatic.TeacherProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProxyTest {
  private static final Logger log = Logger.getLogger(ProxyTest.class.getSimpleName());

  @Test
  @DisplayName("静态代理")
  void staticProxyTest() {
    Teacher teacher = new TeacherImpl();
    TeacherProxy proxy = new TeacherProxy(teacher);
    proxy.teach();
  }

  @Test
  @DisplayName("jdk动态代理")
  void dynamincProxyTest() {
    //创建目标对象
    Student student = new StudentImpl();
    //创建代理对象
    Student proxy = (Student) new ProxyFactory(student).getProxyInstane();

    //会调用Object.toString()方法，也会被代理增强
    log.info(proxy.toString());
    //class com.sun.proxy.$Proxy12 "$Proxy**"表示内存中动态生存的代理对象
    log.info(proxy.getClass().toString());
    proxy.study();
    assertEquals("How do fish learn to swim?",proxy.ask("How do fish learn to swim?"));
  }
}
