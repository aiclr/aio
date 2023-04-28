package org.bougainvilleas.base.designpattern.liskov;

import java.util.HashMap;
import java.util.Map;

/**
 * 重载=子类自己的方法，跟父类没关系，只是方法名字相同，参数范围大于父类或返回值范围小于父类
 * 重写=改写父类方法 参数，返回值不能变
 */
public class Liskov2 {

    public static void main(String[] args) {
        HashMap m=new HashMap();

        Base2 base2=new Base2();
        base2.func(m);
        base2.put2(m);

        A3 a3=new A3();
        //由于子类重载了父类方法，如果子类没有重写父类，则调用的是父类的方法
        a3.func(m);
        a3.put2(m);
    }

}

class Base2{

    public Integer func(HashMap map){
        System.err.println("父类方法被执行");
        return 1;
    }
    public Map put2(HashMap map){
        System.err.println("父类方法被执行");
        return map;
    }
}
class A3 extends Base2{
    //覆写Overload不是重载Override

    //扩大前置条件
    public Integer func(Map map) {
        System.err.println("子类重载方法被执行");
        return 2;
    }
    //前置扩大，后置缩小
    public HashMap put2(Map map){
        System.err.println("子类重载方法被执行");
        return (HashMap) map;
    }
    //重写则执行此方法
//    @Override
//    public Number func(HashMap map) {
//        System.err.println("子类重写方法被执行");
//        return Integer.valueOf(2);
//    }

}

