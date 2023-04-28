package org.bougainvilleas.base.designpattern.pattern.creation.factory.abstractdemo.store;

import org.bougainvilleas.base.designpattern.pattern.creation.factory.abstractdemo.factory.AbsFactory;
import org.apache.commons.lang3.StringUtils;

import static org.bougainvilleas.base.designpattern.pattern.creation.factory.no.order.Order.getName;


/**
 * 建造者模式
 * 中 指挥者角色
 */
public class Order {

    AbsFactory absFactory;

    public Order(AbsFactory abs) {
        this.absFactory = abs;
        do{
            System.err.println("输入pizza name");
            String name=getName();
            if(StringUtils.isEmpty(name)){
                break;
            }else {
                absFactory.createPizza(name);
            }
        }while (true);
    }
}
