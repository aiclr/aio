package org.bougainvilleas.base.designpattern.pattern.behavior.strategy;

/**
 * 算法使用者
 */
public class Duck {

    public String name;
    public Fly fly;
    public Quack quack;
    public Eat eat;

    public Duck(Eat eat) {
        this.eat = eat;
    }

    public void disPlay() {
        System.err.println(name+" : ");
        this.fly();
        this.quack();
        System.err.println(eat.eat());
    }

    public void fly(){
        fly.fly();
    }

    public void quack(){
        quack.quack();
    }

    public void setFly(Fly fly) {
        this.fly = fly;
    }

    public void setQuack(Quack quack) {
        this.quack = quack;
    }
}


class ToyDuck extends Duck {

    public ToyDuck(Eat eat) {
        super(eat);
        name="玩具鸭";
        fly=new NoFly();
        quack=new JiJi();
    }
}


class PekingDuck extends Duck {

    public PekingDuck(Eat eat) {
        super(eat);
        name="北京鸭";
        fly=new BadFly();
        quack=new GaGa();
    }
}

class WildDuck extends Duck {

    public WildDuck(Eat eat) {
        super(eat);
        name="野鸭";
        fly=new GoodFly();
        quack=new NoQuack();
    }
}