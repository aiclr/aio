package org.bougainvilleas.ilj.designpattern.behavior.mediator;

/**
 * 具体同事类-电视机
 */
public class ColleagueTV extends Colleague{
  public ColleagueTV(Mediator mediator, String name) {
    super(mediator, name);
    /**
     * 创建ColleagueTV对象时,将自己放入到ConcreteMediator对象中[集合]
     */
    mediator.register(name,this);
  }

  public void startTV(){
    log.info("start TV");
  }
  public void stopTV(){
    log.info("stop TV");
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
