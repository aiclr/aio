package org.bougainvilleas.ilj.designpattern.behavior.observer;

import java.util.logging.Logger;

/**
 * 观察者模式,ocp原则
 * 对象之间多对一依赖的一种方案,被依赖对象为Subject,依赖对象为Observer,
 * Subject通知Observer
 * <p>
 * 以集合方式管理Observer,注册,移除,通知
 * 增加具体Observer,不需要修改核心Subject实现类(WeatherData)
 * 遵循OCP原则
 */
public interface Observer {
  Logger log = Logger.getLogger(Observer.class.getSimpleName());

  void update(String data);
}
