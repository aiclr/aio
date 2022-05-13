package org.bougainvilleas.base.designpattern.pattern.creation.factory.abstractdemo.factory;

import org.bougainvilleas.base.designpattern.pattern.creation.factory.method.pizza.BJCheesePizza;
import org.bougainvilleas.base.designpattern.pattern.creation.factory.method.pizza.BJGreekPizza;
import org.bougainvilleas.base.designpattern.pattern.creation.factory.no.pizza.Pizza;

public class BJOrderFactory implements AbsFactory{

    @Override
    public void createPizza(String name){
        Pizza pizza=null;
        if ("cheese".equals(name)){
            pizza= new BJCheesePizza();
        }else if("greek".equals(name)) {
            pizza= new BJGreekPizza();
        }else{
            System.err.println("order failure");
            return;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.bake();
    }
}
