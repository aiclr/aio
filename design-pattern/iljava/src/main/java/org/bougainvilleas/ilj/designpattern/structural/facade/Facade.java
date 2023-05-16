package org.bougainvilleas.ilj.designpattern.structural.facade;

/**
 * 外观模式 分层
 */
public interface Facade {
  /**
   * start时，{@link Model1#on()}，{@link Model2#start()}
   */
  void start();

  /**
   * end是，{@link Model1#off()}，{@link Model2#stop()}
   */
  void end();
}
