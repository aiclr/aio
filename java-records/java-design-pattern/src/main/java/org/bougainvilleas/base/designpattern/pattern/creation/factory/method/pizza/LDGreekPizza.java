package org.bougainvilleas.base.designpattern.pattern.creation.factory.method.pizza;

import org.bougainvilleas.base.designpattern.pattern.creation.factory.no.pizza.Pizza;

public class LDGreekPizza extends Pizza {

    @Override
    public void prepare() {
        setName("LDGreekPizza");
        System.err.println("prepare LDGreekPizza");
    }
}
