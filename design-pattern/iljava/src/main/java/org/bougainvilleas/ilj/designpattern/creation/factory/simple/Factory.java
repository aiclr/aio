package org.bougainvilleas.ilj.designpattern.creation.factory.simple;

import org.bougainvilleas.ilj.designpattern.creation.factory.BJCheesePizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.BJGreekPizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.LDCheesePizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.LDGreekPizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.Pizza;

import java.util.logging.Logger;

/**
 * ocp原则，单一职责原则
 *
 * 当需求变化，比如增加一个Pizza种类时候，只需要修改{@link Factory#getPizza(String)}。符合ocp原则
 *
 * 单例工厂+简单工厂模式 {@link #simpleFactory(String)}
 * 静态工厂模式 {@link #staticFactory(String)}
 *
 * 简单工厂一般配合单例模式使用
 */
public class Factory {

  private static final Logger log = Logger.getLogger(Factory.class.getSimpleName());

  private Factory() {
  }

  private static volatile Factory INSTANCE;

  public static Factory getInstance() {
    if (INSTANCE == null) {
      synchronized (Factory.class) {
        if (INSTANCE == null)
          INSTANCE = new Factory();
      }
    }
    return INSTANCE;
  }


  /**
   * 单例+简单工厂模式
   *
   * @param pizzaType pizza type
   * @return Pizza
   */
  public Pizza simpleFactory(String pizzaType) {
    return getPizza(pizzaType);
  }


  /**
   * 静态工厂模式
   *
   * @param pizzaType pizza type
   * @return Pizza
   */
  public static Pizza staticFactory(String pizzaType) {
    return getPizza(pizzaType);
  }

  /**
   * 抽离公用代码
   * @param pizzaType pizza type
   * @return Pizza
   */
  public static Pizza getPizza(String pizzaType) {
    if (pizzaType == null || pizzaType.equals("")) {
      return null;
    }
    Pizza pizza;
    switch (pizzaType) {
      case "BJCheesePizza":
        pizza = new BJCheesePizza();
        break;
      case "BJGreekPizza":
        pizza = new BJGreekPizza();
        break;
      case "LDCheesePizza":
        pizza = new LDCheesePizza();
        break;
      case "LDGreekPizza":
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
