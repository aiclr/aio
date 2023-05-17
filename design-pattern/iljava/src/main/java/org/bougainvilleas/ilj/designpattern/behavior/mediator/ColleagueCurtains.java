package org.bougainvilleas.ilj.designpattern.behavior.mediator;

/**
 * 具体同事类-窗帘
 */
public class ColleagueCurtains extends Colleague{
  public ColleagueCurtains(Mediator mediator, String name) {
    super(mediator, name);
    /**
     * 创建ColleagueCurtains对象时,将自己放入到ConcreteMediator对象中[集合]
     */
    mediator.register(name,this);
  }

  public void startCurtains(){
    log.info("start Curtains 开窗");
  }
  public void stopCurtains(){
    log.info("stop Curtains 关窗");
  }
  /**
   * 调用中介对象的getMessage
   * @param stateChange
   */
  @Override
  public void sendMessage(int stateChange) {
    this.getMediator().getMessage(stateChange,getName());
  }
}
