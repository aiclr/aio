package org.bougainvilleas.ilj.designpattern.structural.adapter;

import java.util.logging.Logger;

/**
 * 类适配器 将被适配者{@link Volatage220v}转换产品实际需求的规格 - 5V{@link Volatage5V}
 */
public class AdapterClass extends Volatage220v implements Volatage5V {

  private static final Logger log = Logger.getLogger(AdapterClass.class.getSimpleName());

  @Override
  public int outPut5V() {
    int v = super.output220v() / 44;
    log.info("经过适配器电压转换为："+v+"v");
    return v;
  }
}
