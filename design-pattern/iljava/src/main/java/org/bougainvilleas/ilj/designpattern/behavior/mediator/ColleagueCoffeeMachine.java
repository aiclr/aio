package org.bougainvilleas.ilj.designpattern.behavior.mediator;

/**
 * 具体同事类-咖啡机
 */
public class ColleagueCoffeeMachine extends Colleague{
  public ColleagueCoffeeMachine(Mediator mediator, String name) {
    super(mediator, name);
    /**
     * 创建ColleagueCoffeeMachine对象时,将自己放入到ConcreteMediator对象中[集合]
     */
    mediator.register(name,this);
  }

  public void startCoffeeMachine(){
    log.info("start CoffeeMachine");
  }
  public void stopCoffeeMachine(){
    log.info("stop CoffeeMachine");
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
