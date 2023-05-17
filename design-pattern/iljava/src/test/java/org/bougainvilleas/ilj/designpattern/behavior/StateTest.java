package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.state.Activity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-instance-lifecycle">@TestInstance</a>
 * @see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-argument-aggregation">@MethodSource</a>
 */
@DisplayName("状态模式")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StateTest {
  private static final Logger log = Logger.getLogger(StateTest.class.getSimpleName());

  Activity activity;

  @BeforeAll
  void before() {
    activity = new Activity(2);
  }

  @ParameterizedTest
  @MethodSource("range")
  void stateTest(int argument) {
    log.info("奖池"+activity.getCount());
    log.info("第"+argument+"次抽奖");
    activity.deductMoney();
    activity.raffle();
  }

  static IntStream range() {
    //从0到21,从1开始
    return IntStream.range(0, 21).skip(1);
  }
}
