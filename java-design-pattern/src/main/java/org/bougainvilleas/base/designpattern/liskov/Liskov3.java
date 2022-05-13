package org.bougainvilleas.base.designpattern.liskov;

import java.util.HashMap;
import java.util.Map;

/**
 * 子类前置条件范围小于父类前置条件
 * 违反里氏替换原则
 */
public class Liskov3 {

    public static void main(String[] args) {
        //父类
        C1 c1 = new C1();
        c1.get(new HashMap());

        //子类换父类
        C11 c11 = new C11();
        c11.get(new HashMap());

    }

}
class C1{
    public int get(Map map){
        System.err.println("父类");
        return 1;
    }
}
class C11 extends C1{
    public int get(HashMap hm){
        System.err.println("子类");
        return 1;
    }
}
