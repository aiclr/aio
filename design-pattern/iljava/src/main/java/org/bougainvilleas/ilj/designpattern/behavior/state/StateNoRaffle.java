package org.bougainvilleas.ilj.designpattern.behavior.state;

/**
 * 扣除积分状态
 */
public class StateNoRaffle implements State{
  Activity activity;

  public StateNoRaffle(Activity activity) {
    this.activity = activity;
  }


  @Override
  public void deductMoney() {
    log.info("成功扣50积分,可以抽奖");
    activity.setState(activity.getCanRaffle());
  }

  @Override
  public boolean raffle() {
    log.warning("扣除积分后才可以抽奖");
    return false;
  }

  @Override
  public void dispensePrize() {
    log.warning("未抽奖,不能发放奖品");
  }
}
