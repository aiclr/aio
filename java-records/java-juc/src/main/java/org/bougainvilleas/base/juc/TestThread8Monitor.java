package org.bougainvilleas.base.juc;

/**
 * 线程8锁
 *
 * 明确概念：java对象，实例对象 和 Class对象
 * 一个类对应一个Class对象，对应多个new 实例对象
 * 每一个类都有一个Class对象，每当编译一个新类就产生一个Class对象，
 * 每个类的运行时的类型信息就是用Class对象表示的。它包含了与类有关的信息。
 * 可以理解为：实例对象通过Class对象来创建
 *
 * 1.两个普通同步方法，两个线程，标准打印====one two                                (使用同一个锁this=num，one会先抢到锁，two等待）
 * 2.新增Thread.sleep(3000)给getOne2(),依旧是打印===等待3秒打印 one two           (使用同一个锁this=num，one会先抢到锁，two等待one释放锁）
 * 3.新增普通非同步方法，与2一起，打印结果===three 等待3秒 one two                   (使用同一个锁this=num，one会先抢到锁，two等待one释放锁，three不需要锁 不用等待one释放锁 直接执行)
 * 4.两个Num对象，两个普通同步方法，打印 === two 等待3秒（其实会小于3秒，多线程） one    (每个对象一个锁num和num4，各用各的，num4的two不会去竞争num的one的锁，即普通同步方法的锁是对象实例持有)
 * 5.getOne5()为静态同步方法，getTwo5为普通同步方法，打印 === two 等待3秒 one        (非静态同步方法two的锁为num，静态同步方法one的锁Class实例)
 * 6.getOne6()和getTwo6()为静态同步方法，打印 === 等待3秒 one two                 (静态同步方法之间会使用同一把锁Class实例，静态方法one获取锁，静态方法two等待one释放锁)
 * 7.两个对象，一个调用非静态同步方法，一个调用静态同步方法，打印====two 等待3秒 one     (one静态方法锁Class实例，two非静态方法锁在num4对象里，不会竞争一把锁)
 * 8.两个对象，两个静态同步方法，打印==== 等待3秒one two                            (静态同步方法之间会使用同一把锁Class实例，one获取锁，two等待one释放锁)
 * 结论：
 * 某一时刻只有一个线程持有锁，无论几个方法
 * 非静态方法的锁默认为this
 * 静态方法的锁为对应的Class实例
 * @author renqiankun
 */
public class TestThread8Monitor {
    public static void main(String[] args) {
        Num num = new Num();
        //1.两个普通同步方法，两个线程，标准打印====one two
//        new Thread(()-> num.getOne1()).start();
//        new Thread(()-> num.getTwo1()).start();
        //2.新增Thread.sleep(3000)给getOne2(),依旧是打印===等待3秒打印 one two
//        new Thread(()-> num.getOne2()).start();
//        new Thread(()-> num.getTwo2()).start();
        //3.新增普通非同步方法，与2一起，打印结果===three 等待3秒 one two
        new Thread(()-> num.getThree3()).start();
        //4.两个Num对象，两个普通同步方法，打印 === 等待3秒 two one
        Num num4 = new Num();
//        new Thread(()-> num.getOne2()).start();
//        new Thread(()-> num4.getTwo2()).start();
        //5.getOne5()为静态同步方法，getTwo5为普通同步方法，同一个对象调用，打印 ===  two 等待3秒 one
//        new Thread(()-> num.getOne5()).start();
//        new Thread(()-> num.getTwo5()).start();
        //6.getOne6()和getTwo6()为静态同步方法，同一个对象调用，打印 === one two
//        new Thread(()-> num.getOne6()).start();
//        new Thread(()-> num.getTwo6()).start();
        //7.两个对象，一个调用非静态同步方法，一个调用静态同步方法，打印====two one
//        new Thread(()-> num.getOne5()).start();
//        new Thread(()-> num4.getTwo5()).start();
        //8.两个对象，两个静态同步方法，打印====one two
        new Thread(()-> num.getOne6()).start();
        new Thread(()-> num4.getTwo6()).start();

    }
}


class Num{

    public synchronized void getOne1(){
        System.out.println("one");
    }

    public synchronized void getTwo1(){
        System.out.println("two");
    }
//
    public synchronized void getOne2(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo2(){
        System.out.println("two");
    }
//
    public void getThree3(){
        System.out.println("three");
    }
//
    public static synchronized void getOne5(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo5(){
        System.out.println("two");
    }
//
    public static synchronized void getOne6(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo6(){
        System.out.println("two");
    }


}