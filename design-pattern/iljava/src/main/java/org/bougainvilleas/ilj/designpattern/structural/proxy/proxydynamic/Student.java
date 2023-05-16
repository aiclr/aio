package org.bougainvilleas.ilj.designpattern.structural.proxy.proxydynamic;

import java.util.logging.Logger;

/**
 * 必须有接口才能使用jdk动态代理
 */
public interface Student {
  Logger log = Logger.getLogger(Student.class.getSimpleName());
  void study();
  String ask(String question);
}
