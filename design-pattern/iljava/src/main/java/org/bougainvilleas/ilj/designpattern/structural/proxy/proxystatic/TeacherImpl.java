package org.bougainvilleas.ilj.designpattern.structural.proxy.proxystatic;

public class TeacherImpl implements Teacher{
  @Override
  public void teach() {
    log.info("Teacher 正在工作");
  }
}
