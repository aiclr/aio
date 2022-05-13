package org.bougainvilleas.base.designpattern.pattern.creation.singleton.enumtype;

public class SingletonDemo {

    public static void main(String[] args) {

        for(int i=0;i<10;i++) {
            TestThread t = new TestThread();
            new Thread(t).start();
        }
        Demo.INSTANCE.test();
        System.out.println(Demo.INSTANCE.hashCode());
    }
}

/**
 * @Description: 单例模式 枚举  线程安全，由jvm实现 ，防止反序列化重新创建新对象
 * @Author caddy
 * @date 2020-02-07 11:44:15
 * @version 1.0
 */
enum Demo{
    INSTANCE;

    public void test(){
        System.out.println("enum");
    }
}

class TestThread implements Runnable{
    @Override
    public void run() {
        System.err.println(Demo.INSTANCE.hashCode());
    }
}