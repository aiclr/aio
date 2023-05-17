package org.bougainvilleas.ilj.designpattern.behavior.command.editor;

public class ActionClose implements Action {
  private final Editor editor;

  public ActionClose(Editor editor) {
    this.editor = editor;
  }

  @Override
  public void perform() {
    editor.close();
  }
}
