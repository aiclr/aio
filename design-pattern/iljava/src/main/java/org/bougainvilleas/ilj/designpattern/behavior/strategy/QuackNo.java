package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import org.bougainvilleas.ilj.designpattern.behavior.strategy.Quack;

public class QuackNo implements Quack {
  @Override
  public void quack() {
    log.info("不会叫");
  }
}
