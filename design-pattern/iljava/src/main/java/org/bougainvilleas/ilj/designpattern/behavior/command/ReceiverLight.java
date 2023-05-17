package org.bougainvilleas.ilj.designpattern.behavior.command;

import java.util.logging.Logger;

/**
 * 命令接受者-电灯开关<br/>
 * 命令接收者,知道如何实施和执行一个请求相关的操作
 */
public class ReceiverLight {

  private static final Logger log = Logger.getLogger(ReceiverLight.class.getSimpleName());

  public void on() {
    log.info("电灯打开");
  }

  public void off() {
    log.info("电灯关闭");
  }
}
