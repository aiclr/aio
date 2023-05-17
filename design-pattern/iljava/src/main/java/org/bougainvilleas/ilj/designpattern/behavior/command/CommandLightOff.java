package org.bougainvilleas.ilj.designpattern.behavior.command;

/**
 * 关闭电灯命令
 */
public class CommandLightOff implements Command {

  private ReceiverLight receiver;

  public CommandLightOff(ReceiverLight receiver) {
    this.receiver = receiver;
  }

  @Override
  public void execute() {
    receiver.off();
  }

  @Override
  public void undo() {
    receiver.on();
  }
}
