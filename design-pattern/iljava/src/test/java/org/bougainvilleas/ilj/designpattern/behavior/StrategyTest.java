package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.strategy.Duck;
import org.bougainvilleas.ilj.designpattern.behavior.strategy.DuckPeking;
import org.bougainvilleas.ilj.designpattern.behavior.strategy.DuckToy;
import org.bougainvilleas.ilj.designpattern.behavior.strategy.DuckWild;
import org.bougainvilleas.ilj.designpattern.behavior.strategy.FlyGood;
import org.bougainvilleas.ilj.designpattern.behavior.strategy.QuackNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;


/**
 * 策略模式 strategy pattern
 * 将继承换成组合或者聚合。
 * 定义算法簇,分别封装,让他们可以互相替换,此模式让算法的变化独立与使用算法的用户，
 * 把变化的代码从不变的代码中分离出来。
 * 针对接口编程,策略接口。
 * 多用组合/聚合,少用继承--客户通过组合方式使用策略。
 * <p>
 * 对修改关闭,对扩展开放
 * <p>
 * 策略过多,会导致类数目庞大
 * <p>
 * 如果策略带返回值 可以使用 lambda 简化 策略
 */
@DisplayName("策略模式")
class StrategyTest {
  Logger log = Logger.getLogger(StrategyTest.class.getSimpleName());

  @DisplayName("鸭子模式")
  @Test
  void strategyTest() {
    Duck wild = new DuckWild(() -> "吃鱼");
    wild.display();
    Duck toy = new DuckToy(() -> "吃屁");
    toy.display();
    Duck peking = new DuckPeking(() -> "吃饲料");
    peking.display();
    peking.setFly(new FlyGood());
    peking.setQuack(new QuackNo());
    peking.display();
  }

  /**
   * @see Comparator
   */
  @DisplayName("Comparator<T> 策略模式演示")
  @Test
  void comparableTest() {

    Integer[] data = {11, 2, 43, 33, 24, 3};

    Comparator<Integer> comparable =
            new Comparator<Integer>() {
              //升序
              @Override
              public int compare(Integer var1, Integer var2) {
                if (var1.compareTo(var2) > 0) {
                  return 1;
                } else {
                  return -1;
                }
              }
            };

    Arrays.sort(data, comparable);
    log.info(Arrays.toString(data));

    Arrays.sort(data, (var1, var2) -> {
      //降序
      if (var1.compareTo(var2) > 0) {
        return -1;
      } else {
        return 1;
      }
    });

    log.info(Arrays.toString(data));
  }
}
