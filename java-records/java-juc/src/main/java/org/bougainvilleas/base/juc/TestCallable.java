package org.bougainvilleas.base.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable接口
 * 相较于Runnable接口，方法可以有返回值，并且可以抛出异常
 */
public class TestCallable {
    public static void main(String[] args) {

        CallableDemo callableDemo = new CallableDemo();
        callableDemo.setStart(1);
        callableDemo.setEnd(49);
        //Callable有返回值需要接收，使用FutureTask（Future实现类）实现类，接收运算结果
        //FutureTask也能用于闭锁
        FutureTask<Integer> result=new FutureTask<>(callableDemo);
        new Thread(result).start();

        CallableDemo callableDemo2 = new CallableDemo();
        callableDemo2.setStart(50);
        callableDemo2.setEnd(100);

        FutureTask<Integer> result2=new FutureTask<>(callableDemo2);
        new Thread(result2).start();

        try {
            Integer integer = result.get();
            System.out.println("1加到49="+integer);
            Integer integer2 = result2.get();
            System.out.println("50加到100="+integer2);
            System.out.println("两条线程结果汇总得到1加到100="+(integer+integer2));
            System.out.println("检查线程运行中结果有没有执行-----------并没有执行，只有等线程计算出结果后才会获取结果，可以用于闭锁");
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }


    }
}

class CallableDemo implements Callable<Integer>{

    private int start=0;
    private int end=0;

    @Override
    public Integer call() throws Exception {
        int sum =0;
        for (int i =start ; i <= end; i++) {
            sum+=i;
        }
        return sum;
    }

    public void setStart(int start) {
        this.start = start;
    }


    public void setEnd(int end) {
        this.end = end;
    }
}
