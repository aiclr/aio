package org.bougainvilleas.ilj.designpattern.structural.adapter;

import java.util.logging.Logger;

/**
 * 被适配者 - 当前拥有的电源
 */
public class Volatage220v {
  private static final Logger log = Logger.getLogger(Volatage220v.class.getSimpleName());

  public int output220v() {
    log.info("当前电源输出电压220v");
    return 220;
  }
}
