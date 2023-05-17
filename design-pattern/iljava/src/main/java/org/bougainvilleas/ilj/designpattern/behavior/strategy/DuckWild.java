package org.bougainvilleas.ilj.designpattern.behavior.strategy;

public class DuckWild extends Duck {
  public DuckWild(Eat eat) {
    super(eat);
    name = "野鸭";
    fly = new FlyGood();
    quack = new QuackNo();
  }
}
