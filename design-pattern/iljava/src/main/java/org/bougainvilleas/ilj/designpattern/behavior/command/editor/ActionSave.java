package org.bougainvilleas.ilj.designpattern.behavior.command.editor;

public class ActionSave implements Action {
  private final Editor editor;

  public ActionSave(Editor editor) {
    this.editor = editor;
  }

  @Override
  public void perform() {
    editor.save();
  }
}
