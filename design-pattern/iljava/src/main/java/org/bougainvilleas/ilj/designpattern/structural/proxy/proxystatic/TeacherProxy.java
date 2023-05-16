package org.bougainvilleas.ilj.designpattern.structural.proxy.proxystatic;

/**
 * 代理对象，使用此对象调用被代理对象的teach方法
 */
public class TeacherProxy implements Teacher{
  private Teacher teacher;

  //代理目标,根据依赖倒转原则，不要直接new Teacher，使用构造器传入Teacher
  public TeacherProxy(Teacher teacher) {
    this.teacher = teacher;
  }

  @Override
  public void teach() {
    log.info("TeacherProxy before");
    teacher.teach();
    log.info("TeacherProxy after");
  }
}
