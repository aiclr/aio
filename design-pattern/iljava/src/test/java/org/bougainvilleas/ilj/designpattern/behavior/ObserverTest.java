package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.observer.Observer;
import org.bougainvilleas.ilj.designpattern.behavior.observer.ObserverA;
import org.bougainvilleas.ilj.designpattern.behavior.observer.ObserverB;
import org.bougainvilleas.ilj.designpattern.behavior.observer.SubjectWeather;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

@DisplayName("观察者模式")
class ObserverTest {
  private static final Logger log = Logger.getLogger(ObserverTest.class.getSimpleName());

  @Test
  void weatherTest() {
    SubjectWeather weather = new SubjectWeather();
    Observer a = new ObserverA();
    Observer b = new ObserverB();

    weather.setData("12345");
    weather.registerObserver(a);
    weather.registerObserver(b);

    /**
     * lambda 简化观察者模式。（观察者接口只有一个方法）。
     * 可以省略 ObserverA ObserverB 等具体观察者，
     * 直接在lambda中编写业务代码。
     */
    weather.registerObserver(data -> {
      String concat = "Lambda=====".concat(data);
      log.info(concat);
    });
    weather.notifyObserver();
  }
}
