package org.bougainvilleas.base.jvm.runtime;

/**
 * 虚方法表
 *
 *
 */
public class VirtualMethodTableTest {

}

interface Friendly{
    void sayHello();
    void sayGoodBye();
}

class VDog{
    public void sayHello(){

    }
    @Override
    public String toString(){
        return "Dog";
    }
}

class VCat implements Friendly{
    @Override
    public void sayHello() {

    }

    @Override
    public void sayGoodBye() {

    }
    public void eat(){

    }
    @Override
    protected void finalize(){

    }
    @Override
    public String toString(){
        return "cat";
    }
}

class CockerSpaniel extends VDog implements Friendly{
    @Override
    public void sayHello() {
        super.sayHello();
    }

    @Override
    public void sayGoodBye() {

    }
}