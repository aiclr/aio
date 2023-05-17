package org.bougainvilleas.ilj.designpattern.behavior.mediator;

/**
 * 具体同事类-警报器
 */
public class ColleagueAlarm extends Colleague {
  public ColleagueAlarm(Mediator mediator, String name) {
    super(mediator, name);
    /**
     * 创建ColleagueAlarm对象时,将自己放入到ConcreteMediator对象中[集合]
     */
    mediator.register(name, this);
  }

  public void sendAlarm(int stateChange) {
    sendMessage(stateChange);
  }

  /**
   * 调用中介对象的getMessage
   *
   * @param stateChange
   */
  @Override
  public void sendMessage(int stateChange) {
    this.getMediator().getMessage(stateChange, getName());
  }
}
