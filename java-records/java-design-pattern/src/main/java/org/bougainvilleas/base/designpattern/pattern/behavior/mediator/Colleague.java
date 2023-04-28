package org.bougainvilleas.base.designpattern.pattern.behavior.mediator;

import lombok.Getter;
import lombok.Setter;

/**
 * 同事类
 */
@Getter
@Setter
public abstract class Colleague {
    private Mediator mediator;
    private String name;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public Mediator getMediator() {
        return mediator;
    }
    public abstract void sendMessage(int stateChange);
}


/**
 * 具体同事类
 */
class Alarm extends Colleague{

    public Alarm(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.register(name,this);
    }

    public void sendAlarm(int stateChange){
        sendMessage(stateChange);
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange,getName());

    }
}
/**
 * 具体同事类
 */
class TV extends Colleague{

    public TV(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.register(name,this);
    }

    public void startTV(){
        System.err.println("start TV");
    }
    public void stopTV(){
        System.err.println("stop TV");
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void sendMessage(int stateChange) {
        getMediator().getMessage(stateChange,getName());

    }
}


/**
 * 具体同事类
 */
class Curtains extends Colleague{

    public Curtains(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.register(name,this);
    }

    public void startCurtains(){
        System.err.println("start Curtains 开窗");
    }
    public void stopCurtains(){
        System.err.println("stop Curtains 关窗");
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void sendMessage(int stateChange) {
        getMediator().getMessage(stateChange,getName());

    }
}

/**
 * 具体同事类
 */
class CoffeeMachine extends Colleague{

    public CoffeeMachine(Mediator mediator, String name) {
        super(mediator, name);
        //创建Alarm对象时,将自己放入到ConcreteMediator对象中[集合]
        mediator.register(name,this);
    }

    public void startCoffeeMachine(){
        System.err.println("start CoffeeMachine");
    }
    public void stopCoffeeMachine(){
        System.err.println("stop CoffeeMachine");
    }

    /**
     * 调用中介对象的getMessage
     * @param stateChange
     */
    @Override
    public void sendMessage(int stateChange) {
        getMediator().getMessage(stateChange,getName());

    }
}
