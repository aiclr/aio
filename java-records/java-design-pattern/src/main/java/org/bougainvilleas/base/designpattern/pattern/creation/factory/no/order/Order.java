package org.bougainvilleas.base.designpattern.pattern.creation.factory.no.order;

import org.bougainvilleas.base.designpattern.pattern.creation.factory.no.pizza.CheesePizza;
import org.bougainvilleas.base.designpattern.pattern.creation.factory.no.pizza.GreekPizza;
import org.bougainvilleas.base.designpattern.pattern.creation.factory.no.pizza.Pizza;
import org.bougainvilleas.base.designpattern.pattern.creation.factory.simple.factory.SimpleFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: 订购pizza
 * @Author caddy
 * @date 2020-02-10 10:32:02
 * @version 1.0
 */
public class Order {

    public static void main(String[] args) {

        System.out.println("不使用工厂方法");
        noFactory();

        /**
         * 简单工厂方法模式&单例模式
         */
        System.out.println("使用简单工厂");
        SimpleFactory.getInstance().getPizza(getName());
        System.out.println("使用静态工厂");
        SimpleFactory.getPizza2(getName());

    }

    /**
     * 不是用工厂方法生产Pizza
     */
    public static void noFactory(){
        Pizza pizza=null;
        String name="";
        do{
            name=getName();
            if(StringUtils.isEmpty(name)){
                break;
            }else if("cheese".equals(name)){
                pizza=new CheesePizza();
            }else if("greek".equals(name)){
                pizza=new GreekPizza();
            }else{
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.bake();
        }while (true);
    }

    /**
     * 输入交互方法
     */
    public static String getName(){
        BufferedReader string=new BufferedReader(new InputStreamReader(System.in));
        System.err.println("wait input... cheese or greek");
        String name= null;
        try {
            name = string.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
