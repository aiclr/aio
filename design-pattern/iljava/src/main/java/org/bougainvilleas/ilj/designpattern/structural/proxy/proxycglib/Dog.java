package org.bougainvilleas.ilj.designpattern.structural.proxy.proxycglib;

import java.util.logging.Logger;

/**
 * 被代理类 不需要是接口
 */
public class Dog {

  private static final Logger log = Logger.getLogger(Dog.class.getSimpleName());

  public String bark() {
    log.info("Dog is barking");
    return "汪汪";
  }

  /**
   * 无法使用对象调用static方法
   * 因为 static 方法的local variables中不存在this
   */
  public static String bark2() {
    log.info("static method is running");
    return "static";
  }

  /**
   * final 无法被代理
   * final 无法被子类重写
   *
   * @return
   */
  public final String bark3() {
    log.info("final method is running");
    return "final";
  }
}
