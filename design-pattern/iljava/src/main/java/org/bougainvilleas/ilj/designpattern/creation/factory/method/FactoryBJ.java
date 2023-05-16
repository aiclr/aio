package org.bougainvilleas.ilj.designpattern.creation.factory.method;

import org.bougainvilleas.ilj.designpattern.creation.factory.BJCheesePizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.BJGreekPizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.Pizza;

public class FactoryBJ extends Factory {

  private FactoryBJ() {
  }

  private static FactoryBJ INSTANCE = new FactoryBJ();

  public static FactoryBJ getInstance() {
    return INSTANCE;
  }


  @Override
  public Pizza createPizza(String pizzaType) {
    if (pizzaType == null || pizzaType.equals("")) {
      return null;
    }
    Pizza pizza;
    switch (pizzaType) {
      case "Cheese":
        pizza = new BJCheesePizza();
        break;
      case "Greek":
        pizza = new BJGreekPizza();
        break;
      default:
        log.warning(pizzaType + " order failure!");
        return null;
    }
    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();
    return pizza;
  }
}
