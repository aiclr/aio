package org.bougainvilleas.ilj.designpattern.creation.factory;

import org.bougainvilleas.ilj.designpattern.creation.factory.abstracts.FactoryBJ;
import org.bougainvilleas.ilj.designpattern.creation.factory.abstracts.FactoryLD;
import org.bougainvilleas.ilj.designpattern.creation.factory.abstracts.OrderPizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.simple.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryTest {

  private static final Logger log = Logger.getLogger(FactoryTest.class.getSimpleName());

  @DisplayName("静态工厂模式-正常参数")
  @ParameterizedTest
  //{@link https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-ValueSource}
  @ValueSource(strings = {"BJCheesePizza", "BJGreekPizza", "LDCheesePizza", "LDGreekPizza"})
  void staticFactory(String pizzaType) {
    Pizza pizza = Factory.staticFactory(pizzaType);
    assertInstanceOf(Pizza.class, pizza);
  }

  @DisplayName("静态工厂模式-异常参数")
  @ParameterizedTest
  //{@link https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-null-and-empty}
  @NullAndEmptySource// null and ""
  @ValueSource(strings = {"nopizza"})
  void staticFactoryNULL(String pizzaType) {
    assertNull(Factory.staticFactory(pizzaType));
  }

  @DisplayName("简单工厂模式-正常参数")
  @ParameterizedTest
  //{@link https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-ValueSource}
  @ValueSource(strings = {"BJCheesePizza", "BJGreekPizza", "LDCheesePizza", "LDGreekPizza"})
  void simpleFactory(String pizzaType) {
    Pizza pizza = Factory.getInstance().simpleFactory(pizzaType);
    assertInstanceOf(Pizza.class, pizza);
  }

  @DisplayName("简单工厂模式-正常参数")
  @ParameterizedTest
  //{@link https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-null-and-empty}
  @NullAndEmptySource// null and ""
  @ValueSource(strings = {"nopizza"})
  void simpleFactoryNULL(String pizzaType) {
    assertNull(Factory.getInstance().simpleFactory(pizzaType));
  }

  @DisplayName("抽象工厂模式-正常参数")
  @ParameterizedTest
  @CsvSource({
          "Cheese, BJ",
          "Greek, BJ",
          "Cheese, LD",
          "Greek, LD"
  })//{@link https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-argument-aggregation}
  void abstractFactory(String pizzaType, String factoryType) {
    OrderPizza order = new OrderPizza(factoryType);
    assertTrue(order.getFactory() instanceof FactoryLD ||order.getFactory() instanceof FactoryBJ);
    assertInstanceOf(Pizza.class, order.getPizza(pizzaType));
  }

  @DisplayName("工厂方法模式")
  @Nested//{@link https://junit.org/junit5/docs/current/user-guide/#writing-tests-nested}
  class MethodFactory {

    org.bougainvilleas.ilj.designpattern.creation.factory.method.OrderPizza orderBJ;
    org.bougainvilleas.ilj.designpattern.creation.factory.method.OrderPizza orderLD;

    @DisplayName("工厂方法模式-获得工厂-正常参数")
    @Test
    @BeforeEach
    void factoryTest() {
      orderBJ = new org.bougainvilleas.ilj.designpattern.creation.factory.method.OrderPizza("BJ");
      orderLD = new org.bougainvilleas.ilj.designpattern.creation.factory.method.OrderPizza("LD");
      assertInstanceOf(org.bougainvilleas.ilj.designpattern.creation.factory.method.FactoryBJ.class,orderBJ.getFactory());
      assertInstanceOf(org.bougainvilleas.ilj.designpattern.creation.factory.method.FactoryLD.class,orderLD.getFactory());
    }

    @DisplayName("工厂方法模式-生产产品-正常参数")
    @Test
    void pizzaTest() {
      assertInstanceOf(LDGreekPizza.class,orderLD.getPizza("Greek"));
      assertInstanceOf(LDCheesePizza.class,orderLD.getPizza("Cheese"));
      assertInstanceOf(BJGreekPizza.class,orderBJ.getPizza("Greek"));
      assertInstanceOf(BJCheesePizza.class,orderBJ.getPizza("Cheese"));
    }
  }


}
