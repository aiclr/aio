package org.bougainvilleas.ilj.designpattern.creation.factory.method;

import org.bougainvilleas.ilj.designpattern.creation.factory.Pizza;
import org.bougainvilleas.ilj.designpattern.creation.factory.abstracts.OrderPizza;

import java.util.logging.Logger;

public abstract class Factory {
  protected static final Logger log = Logger.getLogger(OrderPizza.class.getSimpleName());
  public abstract Pizza createPizza(String pizzaType);
}
