package org.bougainvilleas.ilj.study.chapter03;

import java.util.ArrayList;
import java.util.Collection;

public class UsingCollection
{
    public static void main(String[] args) {
        ArrayList<String> lst = new ArrayList<>();
        Collection<String> col=lst;

        lst.add("a");
        lst.add("b");
        lst.add("c");

        // 此处目标类型为 ArrayList<String> 所以分派 ArrayList.remove(int var1)
        lst.remove(0);

        //java 基于目标对象的类型简单分配方法 (通过 字节码可以看出来)
        //此处目标类型为 Collection<String> 所以分派 Collection.remove(Object var1)
        col.remove(0);

        System.err.println(lst.size());
        System.err.println(col.size());
    }
}
