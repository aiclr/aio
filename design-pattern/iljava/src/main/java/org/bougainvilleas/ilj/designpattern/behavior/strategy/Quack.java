package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import java.util.logging.Logger;

/**
 * 算法
 */
public interface Quack {
  Logger log = Logger.getLogger(Quack.class.getSimpleName());

  void quack();
}
