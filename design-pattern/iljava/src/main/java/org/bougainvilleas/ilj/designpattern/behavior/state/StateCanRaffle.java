package org.bougainvilleas.ilj.designpattern.behavior.state;

import java.util.Random;

/**
 * 抽奖状态
 */
public class StateCanRaffle implements State{
  Activity activity;

  public StateCanRaffle(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void deductMoney() {
    log.warning("已经扣取过积分");
  }

  @Override
  public boolean raffle() {
    log.info("正在抽奖...");
    Random r = new Random();
    int num = r.nextInt(10);
    if (num == 0) {
      //抽中设置为可以发放奖品状态
      activity.setState(activity.getDispense());
      return true;
    } else {
      //未抽中设置为 未扣积分状态
      log.info("未中奖");
      activity.setState(activity.getNoRaffle());
      return false;
    }
  }

  @Override
  public void dispensePrize() {
    log.info("正在抽奖,还不能领奖品");
  }
}
