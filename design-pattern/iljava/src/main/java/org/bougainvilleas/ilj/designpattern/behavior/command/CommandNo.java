package org.bougainvilleas.ilj.designpattern.behavior.command;

/**
 * 空命令,可省去对空的判断<br/>
 * 没有任何命令,空执行,可以初始化每个按钮<br/>
 * 当调用空命令时,对象什么都不做
 */
public class CommandNo implements Command {
  @Override
  public void execute() {
    log.info("空命令");
  }

  @Override
  public void undo() {
    log.info("空命令");
  }
}
