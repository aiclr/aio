package org.bougainvilleas.base.designpattern.pattern.behavior.command.editor;

public class Client {
  public static void main(String[] args) {
    Client client = new Client();
    client.custom();
    client.anonymousDemo();
    client.lambdaDemo();
  }


  public void custom(){
    Macro macro = new Macro();
    Editor editor = new Editor();
    macro.record(new Open(editor));
    macro.record(new Save(editor));
    macro.record(new Close(editor));
    macro.run();
  }

  /**
   * 匿名内部类版本
   */
  public void anonymousDemo(){
    Macro macro = new Macro();
    Editor editor = new Editor();
    macro.record(new Action() {
      @Override
      public void perform() {
        editor.open();
      }
    });
    macro.record(new Action() {
      @Override
      public void perform() {
        editor.save();
      }
    });
    macro.record(new Action() {
      @Override
      public void perform() {
        editor.close();
      }
    });
    macro.run();
  }
  /**
   * 使用 lambda 优化 命令模式
   * 可以省略具体命令实现类
   * 限制条件是 Action 仅有一个方法
   */
  public void lambdaDemo(){
    Macro macro = new Macro();
    Editor editor = new Editor();
    macro.record(editor::open);
    macro.record(editor::save);
    macro.record(editor::close);
    macro.run();
  }
}
