package org.bougainvilleas.ilj.designpattern.structural;

import org.bougainvilleas.ilj.designpattern.structural.decorator.CaffeeDecaf;
import org.bougainvilleas.ilj.designpattern.structural.decorator.CaffeeLongBlack;
import org.bougainvilleas.ilj.designpattern.structural.decorator.DecoratorChocolate;
import org.bougainvilleas.ilj.designpattern.structural.decorator.DecoratorMilk;
import org.bougainvilleas.ilj.designpattern.structural.decorator.Drink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

@DisplayName("装饰者模式")
class DecoratorTest {

  private static final Logger log = Logger.getLogger(DecoratorTest.class.getSimpleName());

  @Test
  void decoratorTest() {
    Drink drink = new CaffeeDecaf();
    log.info(drink.getDes());
    Assertions.assertEquals(4.0f,drink.cost());
    drink = new DecoratorMilk(drink);
    log.info(drink.getDes());
    Assertions.assertEquals(6.0f,drink.cost());

    drink = new DecoratorMilk(drink);
    log.info(drink.getDes());
    Assertions.assertEquals(8.0f,drink.cost());

    drink = new DecoratorChocolate(drink);
    log.info(drink.getDes());
    Assertions.assertEquals(13.0f,drink.cost());

    Drink drink1 = new CaffeeLongBlack();
    log.info(drink1.getDes());
    Assertions.assertEquals(10.0f,drink1.cost());

  }
}
