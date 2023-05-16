package org.bougainvilleas.ilj.designpattern.structural.flyweight;

/**
 * 共享状态
 */
public class StateShared extends FlyWeight{
  private String data;

  public StateShared(String data) {
    this.data = data;
  }

  @Override
  void use(StateUnshared unshared) {
    log.info("当前共享的状态为： "+data+"。不共享的内部状态为： "+unshared.getData());
  }
}
