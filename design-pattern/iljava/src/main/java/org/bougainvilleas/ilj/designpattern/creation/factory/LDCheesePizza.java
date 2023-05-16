package org.bougainvilleas.ilj.designpattern.creation.factory;

public class LDCheesePizza extends Pizza{
  @Override
  public void prepare() {
    setName("LDCheesePizza");
    log.info("prepare LDCheesePizza");
  }
}
