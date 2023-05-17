package org.bougainvilleas.ilj.designpattern.behavior.command.editor;

import java.util.logging.Logger;

/**
 * 编辑器接受命令 执行
 */
public class Editor {
  private static final Logger log = Logger.getLogger(Editor.class.getSimpleName());

  public void save() {
    log.info("save");
  }

  public void open() {
    log.info("open");
  }

  public void close() {
    log.info("close");
  }
}
