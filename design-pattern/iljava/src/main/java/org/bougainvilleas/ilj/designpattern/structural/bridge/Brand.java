package org.bougainvilleas.ilj.designpattern.structural.bridge;

import java.util.logging.Logger;

/**
 * 品牌类型 抽象
 */
public interface Brand {
  Logger log = Logger.getLogger(Brand.class.getSimpleName());

  void open();

  void close();

  void call();
}
