package org.bougainvilleas.ilj.designpattern.behavior.command;

/**
 * 调用者，聚合很多{@link Command}
 */
public class Invoker {

  Command[] onCommands;
  Command[] offCommands;

  Command lastCommand;

  public Invoker() {
    onCommands = new Command[5];
    offCommands = new Command[5];

    /**
     * 使用空命令{@link CommandNo}初始化
     */
    for (int i = 0; i < 5; i++) {
      onCommands[i] = new CommandNo();
      offCommands[i] = new CommandNo();
    }
  }

  public void setCommand(int no, Command onCommand, Command offCommand) {
    onCommands[no] = onCommand;
    offCommands[no] = offCommand;
  }

  public void onButtonWasPushed(int no){
    onCommands[no].execute();
    lastCommand=onCommands[no];
  }

  public void offButtonWasPushed(int no) {
    offCommands[no].execute();
    lastCommand=offCommands[no];
  }

  public void undoButtonWasPushed() {
    lastCommand.undo();
  }

}
