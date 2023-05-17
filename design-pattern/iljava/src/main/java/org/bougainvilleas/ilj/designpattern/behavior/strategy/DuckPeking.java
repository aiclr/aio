package org.bougainvilleas.ilj.designpattern.behavior.strategy;

public class DuckPeking extends Duck {
  public DuckPeking(Eat eat) {
    super(eat);
    name = "北京鸭";
    fly = new FlyBad();
    quack = new QuackGaGa();
  }
}
