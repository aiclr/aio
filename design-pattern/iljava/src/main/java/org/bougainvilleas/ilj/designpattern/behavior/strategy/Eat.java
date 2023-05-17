package org.bougainvilleas.ilj.designpattern.behavior.strategy;

import java.util.logging.Logger;

/**
 * 算法-策略
 * <p>
 * 如果策略带返回值 可以使用 lambda 简化 策略
 */
public interface Eat {
  Logger log = Logger.getLogger(Eat.class.getSimpleName());

  String eat();
}
