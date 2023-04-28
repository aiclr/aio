package org.bougainvilleas.base.designpattern.pattern.creation.factory.method.pizza;

import org.bougainvilleas.base.designpattern.pattern.creation.factory.no.pizza.Pizza;

public class BJGreekPizza extends Pizza {
    @Override
    public void prepare() {
        setName("BJGreekPizza");
        System.err.println("prepare BJGreekPizza");
    }
}
