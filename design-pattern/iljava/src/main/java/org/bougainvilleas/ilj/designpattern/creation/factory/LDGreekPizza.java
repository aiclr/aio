package org.bougainvilleas.ilj.designpattern.creation.factory;

public class LDGreekPizza extends Pizza {
  @Override
  public void prepare() {
    setName("LDGreekPizza");
    log.info("prepare LDGreekPizza");
  }
}
