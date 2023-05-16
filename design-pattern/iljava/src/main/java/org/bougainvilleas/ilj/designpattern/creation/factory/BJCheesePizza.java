package org.bougainvilleas.ilj.designpattern.creation.factory;

public class BJCheesePizza extends Pizza{
  @Override
  public void prepare() {
    setName("BJCheesePizza");
    log.info("prepare BJCheesePizza");
  }
}
