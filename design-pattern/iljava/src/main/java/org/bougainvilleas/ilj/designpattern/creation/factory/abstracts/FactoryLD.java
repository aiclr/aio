package org.bougainvilleas.ilj.designpattern.creation.factory.abstracts;

import org.bougainvilleas.ilj.designpattern.creation.factory.LDCheesePizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.LDGreekPizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.Pizza;

public class FactoryLD implements Factory {
  private FactoryLD() {

  }

  private static FactoryLD INSTANCE;

  static {
    INSTANCE = new FactoryLD();
  }

  public static FactoryLD getInstance() {
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
        pizza = new LDCheesePizza();
        break;
      case "Greek":
        pizza = new LDGreekPizza();
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
