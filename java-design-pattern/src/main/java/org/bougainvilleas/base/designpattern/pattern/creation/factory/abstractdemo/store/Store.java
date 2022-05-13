package org.bougainvilleas.base.designpattern.pattern.creation.factory.abstractdemo.store;


import org.bougainvilleas.base.designpattern.pattern.creation.factory.abstractdemo.factory.BJOrderFactory;
import org.bougainvilleas.base.designpattern.pattern.creation.factory.abstractdemo.factory.LDOrderFactory;

import static org.bougainvilleas.base.designpattern.pattern.creation.factory.no.order.Order.getName;


/**
 * 抽象工厂模式
 * 定义接口，定义创建对象的方法
 * 由具体子类决定要实例化的对象
 */
public class Store {

    public static void main(String[] args) {
        do{
            System.err.println("输入bj or ld");
            String name=getName();
            if("bj".equals(name)){
                new Order(new BJOrderFactory());
            }else if("ld".equals(name)){
                new Order(new LDOrderFactory());
            }else{
              break;
            }
        }while (true);
    }
}
