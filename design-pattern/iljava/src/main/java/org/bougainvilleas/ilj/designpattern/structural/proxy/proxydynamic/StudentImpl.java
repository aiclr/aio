package org.bougainvilleas.ilj.designpattern.structural.proxy.proxydynamic;

/**
 * 被代理的接口实现类
 */
public class StudentImpl implements Student {
  @Override
  public void study() {
    log.info("Student is studying");
  }

  @Override
  public String ask(String question) {
    log.info(question);
    return question;
  }
}
