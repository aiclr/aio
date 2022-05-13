package org.bougainvilleas.base.jvm.runtime;

public class BindingTest {

    //invokevirtual 虚函数 对应c++ virtual虚函数
    /**
     * 0 aload_1
     * 1 invokevirtual #2 <org/bougainvillea/java/jvm/runtime/Animal.eat>
     * 4 return
     */
    public void showAnimal(Animal animal){
        animal.eat();//late binding
    }

    /**
     * 0 aload_1
     * 1 invokeinterface #3 <org/bougainvillea/java/jvm/runtime/HuntAble.hunt> count 1
     * 6 return
     */
    public void showHunt(HuntAble huntAble){
        huntAble.hunt();//late binding
    }

    public static void main(String[] args) {

    }
}

abstract class Animal{
    abstract void eat();

}

interface HuntAble{
    void hunt();
}

class Dog extends Animal implements HuntAble{
    @Override
    void eat() {
        System.out.println("骨头");
    }

    @Override
    public void hunt() {
        System.out.println("狗拿耗子，多管闲事");
    }
}

class Cat extends Animal implements HuntAble{

    //invokespecial
    /**
     * 0 aload_0
     * 1 invokespecial #1 <org/bougainvillea/java/jvm/runtime/Animal.<init>>
     * 4 return
     */
    public Cat() {
        super();//early binding
    }

    /**
     * 0 aload_0
     * 1 invokespecial #2 <org/bougainvillea/java/jvm/runtime/Cat.<init>>
     * 4 return
     */
    public Cat(String name){
        this();//early binding
    }

    @Override
    void eat() {
        System.out.println("鱼");
    }

    @Override
    public void hunt() {
        System.out.println("猫抓耗子，天经地义");
    }
}
