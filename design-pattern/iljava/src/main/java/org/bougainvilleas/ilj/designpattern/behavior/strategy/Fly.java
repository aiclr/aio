package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import java.util.logging.Logger;

/**
 * 算法
 */
public interface Fly {
  Logger log = Logger.getLogger(Fly.class.getSimpleName());

  void fly();
}
