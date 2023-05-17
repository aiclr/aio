package org.bougainvilleas.ilj.designpattern.behavior.state;

/**
 * 发放奖品状态
 */
public class StateDispense implements State {

  Activity activity;

  public StateDispense(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void deductMoney() {
    log.warning("正在发奖品,不能扣积分");
  }

  @Override
  public boolean raffle() {
    log.warning("正在发奖品,不能抽奖");
    return false;
  }

  @Override
  public void dispensePrize() {
    if (activity.getCount() > 0) {
      activity.setCount(activity.getCount() - 1);
      log.info("恭喜中奖");
      if (activity.getCount() <= 0) {
        activity.setState(activity.getDispenseOut());
      } else {
        activity.setState(activity.getNoRaffle());
      }
    } else {
      log.info("奖品派完了");
      activity.setState(activity.getDispenseOut());
    }
  }
}
