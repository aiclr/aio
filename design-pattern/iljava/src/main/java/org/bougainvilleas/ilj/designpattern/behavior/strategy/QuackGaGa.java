package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import org.bougainvilleas.ilj.designpattern.behavior.strategy.Quack;

public class QuackGaGa implements Quack {
  @Override
  public void quack() {
    log.info("嘎嘎叫");
  }
}
