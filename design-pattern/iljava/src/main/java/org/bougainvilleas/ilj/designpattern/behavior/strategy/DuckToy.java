package org.bougainvilleas.ilj.designpattern.behavior.strategy;

public class DuckToy extends Duck {
  public DuckToy(Eat eat) {
    super(eat);
    name = "玩具鸭";
    fly = new FlyNo();
    quack = new QuackJiJi();
  }
}
