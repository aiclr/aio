package org.bougainvilleas.ilj.designpattern.behavior.mediator;

/**
 * 中介模式 Mediator Pattern
 * 用一个中介对象{@link MediatorConcrete}封装一系列对象{@link Colleague}交互<br/>
 * 中介对象{@link MediatorConcrete}使各个对象{@link Colleague}不需要显示地相互引用,从而使其耦合松散,而且可以独立地改变他们之间的交互<br/>
 * <p>
 * mvc模式,Controller是Model和View的中介、前后端交互的中介
 * <p>
 * 多个类相互耦合,形成网状结构,使用中介模式,可以分离解耦成星状结构<br/>
 * 减少类依赖,降低耦合,符合迪米特法则<br/>
 * 中介承担较多责任,中介者出问题,整个系统都会受到影响<br/>
 * 设计不当中介对象容易过于复杂
 */
public abstract class Mediator {
  //将中介的对象放到集合里
  public abstract void register(String colleagueName, Colleague colleague);

  //接收由具体的同事对象发出的消息
  public abstract void getMessage(int stateChange, String colleagueName);

  //自己也可以发消息
  public abstract void sendMessage();
}
