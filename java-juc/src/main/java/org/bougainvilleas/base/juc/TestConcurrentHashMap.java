package org.bougainvilleas.base.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 分段锁机制
 * java5.0 java.util.concurrent包中提供了多种并发容器类来改进同步容器性能
 *
 * concurrentHashMap同步容器类是java5增加的一个线程安全的哈希表，
 * 对于多线程的操作，介于HashMap和HashTable之间，
 * 内部采用分段锁机制替代HashTable的独占锁
 * jdva8取消分段锁，采用CAS无锁机制
 *
 * 还提供了设计用于多线程上下文中的Collection实现：
 * ConcurrentHashMap 多线程表现优于同步的HashMap
 * ConcurrentSkipListMap 优于同步的TreeMap
 * ConcurrentSkipListSet
 * CopyOnWriteArrayList 当期望的读数和遍历远远大于列表的更新数时，优于同步的ArrayList，每次写入时都会复制一份集合，所以没有并发修改异常
 * CopyOnWriteArraySet
 *
 * @author renqiankun
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        TestCopyOnWriteArrayList cwal = new TestCopyOnWriteArrayList();
        for (int i = 0; i < 10; i++) {
            new Thread(cwal).start();
        }
    }

}

/**
 * CopyOnWriteArrayList不适合添加操作多的
 * 每次添加都会复制开销非常大
 * 适合并发迭代器操作
 */
class TestCopyOnWriteArrayList implements Runnable{

    //Collections.synchronizedList将ArrayList内方法全部转换为同步方法
//    private static List<String> list=Collections.synchronizedList(new ArrayList<>());
    private static CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it=list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            list.add("DD");
        }
    }
}