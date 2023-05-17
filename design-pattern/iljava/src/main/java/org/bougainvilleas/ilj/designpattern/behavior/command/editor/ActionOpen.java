package org.bougainvilleas.ilj.designpattern.behavior.command.editor;

public class ActionOpen implements Action {
  private final Editor editor;

  public ActionOpen(Editor editor) {
    this.editor = editor;
  }

  @Override
  public void perform() {
    editor.open();
  }
}
