package org.bougainvilleas.ilj.designpattern.structural.decorator;

/**
 * 装饰者
 */
public class Decorator extends Drink{

  private Drink drink;

  public Decorator(Drink drink) {
    this.drink = drink;
  }

  @Override
  public float cost() {
    //super.getCost()自己的价格
    //drink.cost()待包装类的价格
    return super.getCost()+drink.cost();
  }

  @Override
  public String getDes() {
    return super.getDes()+" "+super.getCost()+" "+drink.getDes();
  }
}
