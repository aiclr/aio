package org.bougainvilleas.ilj.designpattern.behavior.observer;

import java.util.logging.Logger;

/**
 * 订阅者
 */
public interface Subject {
  Logger log = Logger.getLogger(Subject.class.getSimpleName());

  /**
   * 注册观察者
   */
  void registerObserver(Observer observer);

  /**
   * 移除观察者
   */
  void removeObserver(Observer observer);

  /**
   * 通知观察者
   */
  void notifyObserver();

}
