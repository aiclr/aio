package org.bougainvilleas.ilj.study.chapter18;

public class CodeWithHeavierDependenciesJava {
    public int someAction()
    {
        try{
            Thread.sleep(5000);//模拟消耗时间的动作
        }catch(InterruptedException ex){}
        return (int)(Math.random()*100);//模拟某个动作的结果
    }

    public void myMethod() {
        int value = someAction() + 10;
        System.out.println(value);
    }
}
