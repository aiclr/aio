package org.bougainvilleas.ilj.designpattern.structural.decorator;

/**
 * 具体的装饰类
 */
public class DecoratorMilk extends Decorator {

  public DecoratorMilk(Drink drink) {
    super(drink);
    setDes("Milk");
    setCost(2.0f);
  }
}
