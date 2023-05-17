package org.bougainvilleas.ilj.designpattern.behavior.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * 中介实现类
 */
public class MediatorConcrete extends Mediator{

  private Map<String, Colleague> colleagueMap;
  private Map<String, String> interMap;

  public MediatorConcrete() {
    colleagueMap = new HashMap<>();
    interMap = new HashMap<>();
  }

  @Override
  public void register(String colleagueName, Colleague colleague) {
    colleagueMap.put(colleagueName, colleague);
    if(colleague instanceof ColleagueAlarm){
      interMap.put("Alarm",colleagueName);
    }else if(colleague instanceof ColleagueTV){
      interMap.put("TV",colleagueName);
    }else if(colleague instanceof ColleagueCoffeeMachine){
      interMap.put("CoffeeMachine",colleagueName);
    }else if(colleague instanceof ColleagueCurtains){
      interMap.put("Curtains",colleagueName);
    }
  }

  /**
   * 中介核心方法
   * 根据得到的消息,完成任务
   * 协调各个同事对象,完成任务
   * @param stateChange
   * @param colleagueName
   */
  @Override
  public void getMessage(int stateChange, String colleagueName) {
    if (colleagueMap.get(colleagueName) instanceof ColleagueTV) {
      if(stateChange==0){
        ((ColleagueCurtains)colleagueMap.get(interMap.get("Curtains"))).stopCurtains();
        ((ColleagueCoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).startCoffeeMachine();
        ((ColleagueTV)colleagueMap.get(interMap.get("TV"))).startTV();
      }else if(stateChange==1){
        ((ColleagueTV)colleagueMap.get(interMap.get("TV"))).stopTV();
        ((ColleagueCoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).stopCoffeeMachine();
        ((ColleagueCurtains)colleagueMap.get(interMap.get("Curtains"))).startCurtains();
      }
    }else if (colleagueMap.get(colleagueName) instanceof ColleagueAlarm) {
      if(stateChange==0){
        ((ColleagueCurtains)colleagueMap.get(interMap.get("Curtains"))).stopCurtains();
        ((ColleagueCoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).stopCoffeeMachine();
      }else if(stateChange==1){
        ((ColleagueCurtains)colleagueMap.get(interMap.get("Curtains"))).startCurtains();
        ((ColleagueCoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).startCoffeeMachine();
      }
    }else if (colleagueMap.get(colleagueName) instanceof ColleagueCoffeeMachine) {
      if(stateChange==0){
        ((ColleagueCoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).stopCoffeeMachine();
      }else if(stateChange==1){
        ((ColleagueCoffeeMachine)colleagueMap.get(interMap.get("CoffeeMachine"))).startCoffeeMachine();
      }
    }

  }

  /**
   * 自己也可以发消息
   */
  @Override
  public void sendMessage() {
    throw new UnsupportedOperationException();
  }
}
