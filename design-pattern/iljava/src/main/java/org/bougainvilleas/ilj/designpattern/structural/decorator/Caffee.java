package org.bougainvilleas.ilj.designpattern.structural.decorator;

public class Caffee extends Drink {
  @Override
  public float cost() {
    return getCost();
  }
}
