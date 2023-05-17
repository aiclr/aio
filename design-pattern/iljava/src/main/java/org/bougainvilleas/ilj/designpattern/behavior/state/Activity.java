package org.bougainvilleas.ilj.designpattern.behavior.state;

/**
 * 抽奖活动
 */
public class Activity {
  private State state = null;

  private int count = 0;
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

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getNoRaffle() {
    return noRaffle;
  }

  public void setNoRaffle(State noRaffle) {
    this.noRaffle = noRaffle;
  }

  public State getCanRaffle() {
    return canRaffle;
  }

  public void setCanRaffle(State canRaffle) {
    this.canRaffle = canRaffle;
  }

  public State getDispense() {
    return dispense;
  }

  public void setDispense(State dispense) {
    this.dispense = dispense;
  }

  public State getDispenseOut() {
    return dispenseOut;
  }

  public void setDispenseOut(State dispenseOut) {
    this.dispenseOut = dispenseOut;
  }
}
