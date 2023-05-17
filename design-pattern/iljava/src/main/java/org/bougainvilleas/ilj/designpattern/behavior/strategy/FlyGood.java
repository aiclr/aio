package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import org.bougainvilleas.ilj.designpattern.behavior.strategy.Fly;

public class FlyGood implements Fly {

  @Override
  public void fly() {
    log.info("善于飞行");
  }
}
