package org.bougainvilleas.ilj.designpattern.structural.decorator;

/**
 * 具体的装饰类
 */
public class DecoratorChocolate extends Decorator {

  public DecoratorChocolate(Drink drink) {
    super(drink);
    setDes("Chocolate");
    setCost(5.0f);
  }
}
