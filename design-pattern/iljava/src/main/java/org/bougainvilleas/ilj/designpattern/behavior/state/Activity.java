package org.bougainvilleas.ilj.designpattern.behavior.state;

/**
 * 抽奖活动
 */
public class Activity {
  private State state;

  private int count;
  private State noRaffle = new StateNoRaffle(this);
  private State canRaffle = new StateCanRaffle(this);
  private State dispense = new StateDispense(this);
  private State dispenseOut = new StateDispenseOut(this);

  public Activity(int count) {
    this.state = noRaffle;
    this.count = count;
  }

  public void deductMoney() {
    state.deductMoney();
  }

  public void raffle() {
    if (state.raffle()) {
      state.dispensePrize();
    }
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getNoRaffle() {
    return noRaffle;
  }

  public State getCanRaffle() {
    return canRaffle;
  }

  public State getDispense() {
    return dispense;
  }

  public State getDispenseOut() {
    return dispenseOut;
  }

}
