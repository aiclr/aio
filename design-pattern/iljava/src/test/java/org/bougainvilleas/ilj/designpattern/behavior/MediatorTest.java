package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.mediator.ColleagueAlarm;
import org.bougainvilleas.ilj.designpattern.behavior.mediator.ColleagueCoffeeMachine;
import org.bougainvilleas.ilj.designpattern.behavior.mediator.ColleagueCurtains;
import org.bougainvilleas.ilj.designpattern.behavior.mediator.ColleagueTV;
import org.bougainvilleas.ilj.designpattern.behavior.mediator.Mediator;
import org.bougainvilleas.ilj.designpattern.behavior.mediator.MediatorConcrete;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("中介模式")
class MediatorTest {

  @Test
  void mediatorTest() {
    Mediator mediator = new MediatorConcrete();

    ColleagueAlarm alarm=new ColleagueAlarm(mediator,"alarm");
    ColleagueTV tv=new ColleagueTV(mediator,"TV");
    ColleagueCoffeeMachine coffeeMachine=new ColleagueCoffeeMachine(mediator,"CoffeeMachine");
    ColleagueCurtains curtains=new ColleagueCurtains(mediator,"Curtains");

    alarm.sendMessage(0);
    tv.sendMessage(0);
    coffeeMachine.sendMessage(1);
    curtains.sendMessage(1);
  }
}
