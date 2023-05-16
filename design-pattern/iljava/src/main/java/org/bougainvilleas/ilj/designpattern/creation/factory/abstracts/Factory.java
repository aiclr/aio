package org.bougainvilleas.ilj.designpattern.creation.factory.abstracts;

import org.bougainvilleas.ilj.designpattern.creation.factory.Pizza;

import java.util.logging.Logger;

public interface Factory {
  Logger log = Logger.getLogger(OrderPizza.class.getSimpleName());

  Pizza createPizza(String pizzaType);
}
