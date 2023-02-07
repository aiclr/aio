package org.bougainvilleas.base.designpattern.pattern.behavior.command.editor;

import java.util.ArrayList;
import java.util.List;

public interface Action {
  void perform();
}

/**
 * 编辑器接受命令 执行
 */
class Editor{
  public void save() {
    System.err.println("save");
  }

  public void open() {
    System.err.println("open");
  }

  public void close() {
    System.err.println("close");
  }
}

class Macro{
  private final List<Action> actions;

  public Macro() {
    actions=new ArrayList<>();
  }

  public void record(Action action){
    actions.add(action);
  }

  public void run(){
    actions.forEach(Action::perform);
  }

}

class Close implements Action {
  private final Editor editor;

  public Close(Editor editor) {
    this.editor = editor;
  }

  @Override
  public void perform() {
    editor.close();
  }
}

class Open implements Action {
  private final Editor editor;

  public Open(Editor editor) {
    this.editor = editor;
  }

  @Override
  public void perform() {
    editor.open();
  }
}

class Save implements Action {

  private final Editor editor;

  public Save(Editor editor) {
    this.editor = editor;
  }

  @Override
  public void perform() {
    editor.save();
  }
}
