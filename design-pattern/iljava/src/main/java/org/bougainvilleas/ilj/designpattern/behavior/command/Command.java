package org.bougainvilleas.ilj.designpattern.behavior.command;

import java.util.logging.Logger;

/**
 * 命令模式<br/>
 * 1. 将发起请求的对象与执行请求的对象解耦.<br/>
 * 发起请求的对象是调用者{@link Invoker},调用者只要调用命令对象的{@link Command#execute()}方法就可以让接收者{@link ReceiverLight}工作,而不必知道具体的接收者对象是谁<br/>
 * 命令对象{@link Command}会负责让接收者{@link ReceiverLight}执行请求的动作,请求发起者和请求执行者之间的解耦是通过命令对象实现的,命令对象起到了纽带桥梁的作用<br/>
 * 2. 容易设计一个命令队列,只要把命令对象放到队列,就可以多线程的执行命令<br/>
 * 3. 容易实现对请求的撤销和重做<br/>
 * 4. 可能导致某些系统有过多的具体命令类,增加系统的复杂度<br/>
 * 5. 空命令{@link CommandNo}也是一种设计模式,可以省去判空的操作<br/>
 * 6. 经典应用场景,界面的一个按钮就是一条命令,模拟CMD(DOS命令)订单的撤销/恢复,触发-反馈机制<br/>
 * <p>
 * 命令抽象要执行的命令都在这里，可以是接口或者抽象类<br/>
 * 将{@link ReceiverLight}和一个动作{@link Command}绑定，调用相应的操作，从而实现execute
 */
public interface Command {
  Logger log = Logger.getLogger(Command.class.getSimpleName());

  /**
   * 执行
   */
  void execute();

  /**
   * 撤销
   */
  void undo();
}
