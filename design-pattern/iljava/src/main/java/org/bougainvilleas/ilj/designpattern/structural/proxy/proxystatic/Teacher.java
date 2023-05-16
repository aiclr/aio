package org.bougainvilleas.ilj.designpattern.structural.proxy.proxystatic;

import java.util.logging.Logger;

/**
 * 接口
 */
public interface Teacher {
  Logger log = Logger.getLogger(Teacher.class.getSimpleName());
  void teach();
}
