package org.bougainvilleas.ilj.designpattern.structural.flyweight;

import java.util.logging.Logger;

/**
 * 享元模式 抽象类
 */
public abstract class FlyWeight {
  protected static final Logger log = Logger.getLogger(FlyWeight.class.getSimpleName());
  abstract void use(StateUnshared unshared);
}
