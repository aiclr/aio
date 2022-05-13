package org.bougainvilleas.base.designpattern.pattern.behavior.state;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽奖活动
 */
@Getter
@Setter
public class Activity {
    State state = null;

    int count = 0;
    State noRaffleState = new NoRaffleState(this);
    State canRaffleState = new CanRaffleState(this);
    State dispenseState = new DispenseState(this);
    State dispenseOutState = new DispenseOutState(this);

    public Activity(int count) {
        this.state = getNoRaffleState();
        this.count = count;
    }

    public void debuctMoney() {
        state.deduceMoney();
    }

    public void raffle() {
        if (state.raffle()) {
            state.dispensePrize();
        }
    }
}
