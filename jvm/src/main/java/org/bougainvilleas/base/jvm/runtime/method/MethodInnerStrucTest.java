package org.bougainvilleas.base.jvm.runtime.method;

import java.io.Serializable;

/**
 * 方法区结构
 */
public class MethodInnerStrucTest extends Object implements Comparable<MethodInnerStrucTest>, Serializable {

    private static final long serialVersionUID=1L;
    //属性
    public int num=10;
    private static String str="bougainvillea";
    //省略构造器
    //方法
    public void test1(){
        int count=10;
        System.out.println("count="+count);
    }
    public static int test2(int cal){
        int result=0;
        try {
            int value=30;
            result=value/cal;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(MethodInnerStrucTest o) {
        return 0;
    }
}
