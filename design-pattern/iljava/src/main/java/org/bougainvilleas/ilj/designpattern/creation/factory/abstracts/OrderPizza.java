package org.bougainvilleas.ilj.designpattern.creation.factory.abstracts;

import org.bougainvilleas.ilj.designpattern.creation.factory.Pizza;

import java.util.logging.Logger;

/**
 * 抽象工厂模式
 * <p>
 * 定义接口{@link Factory}，
 * 定义创建对象的方法{@link Factory#createPizza(String)}，
 * 由具体子类{@link FactoryLD}、{@link FactoryBJ}决定要实例化的产品对象。
 * <p>
 * {@link OrderPizza}建造者模式 中 指挥者角色
 */
public class OrderPizza {

  private static final Logger log = Logger.getLogger(OrderPizza.class.getSimpleName());

  Factory factory;

  public OrderPizza(String factoryType) {
    if (factoryType == null || factoryType.equals("")) {
      log.warning("请选择正确工厂");
      return;
    }
    switch (factoryType) {
      case "BJ":
        factory = FactoryBJ.getInstance();
        break;
      case "LD":
        factory = FactoryLD.getInstance();
        break;
      default:
        log.warning(factoryType + " is unknown!");
        break;
    }
  }

  public Pizza getPizza(String pizzaType) {
    return factory == null ? null : factory.createPizza(pizzaType);
  }

  public Factory getFactory() {
    return factory;
  }
}
