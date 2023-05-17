package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import org.bougainvilleas.ilj.designpattern.behavior.strategy.Quack;

public class QuackJiJi implements Quack {
  @Override
  public void quack() {
    log.info("唧唧叫");
  }
}
