package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import org.bougainvilleas.ilj.designpattern.behavior.strategy.Fly;

public class FlyBad implements Fly {

  @Override
  public void fly() {
    log.info("飞行技术一般");
  }
}
