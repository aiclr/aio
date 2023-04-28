package org.bougainvilleas.base.dsaa;

/**
 * 数组模拟队列
 */
public class ArrayQueue {
    public static void main(String[] args) {
        ArrayQueueDemo demo = new ArrayQueueDemo(3);
        demo.addQueue(1);
        demo.addQueue(2);
        demo.addQueue(3);
        demo.display();
        System.err.println(demo.headQueue());
        System.err.println(demo.getQueue());
        System.err.println(demo.headQueue());
        demo.display();
        demo.addQueue(4);
        demo.display();

        System.err.println("----------");

        ArrayQueueDemo1 demo1 = new ArrayQueueDemo1(4);//实际只有三个位置
        demo1.addQueue(1);
        demo1.addQueue(2);
        demo1.addQueue(3);
        //rear 3
        //front 0
        System.err.println(demo1.getQueue());
        //f=1,r=3
        demo1.addQueue(4);
        //r=0 f=1
        demo1.display();
        System.err.println(demo1.getQueue());
        //f=2 r=0
        System.err.println(demo1.getQueue());
        //f=3 r=0
        demo1.addQueue(5);
        //r=1 f=3
        demo1.display();//2个
        System.err.println(demo1.getQueue());
        //f=1 r=1
        demo1.addQueue(6);
        //f=1 r=2
        demo1.addQueue(7);
        //f=1 r=0
        demo1.display();

    }
}

/**
 * 模拟简单队列
 * 一次性,不能重复使用
 */
class ArrayQueueDemo{
    private int maxSize;//最大容量
    private int front;//头
    private int rear;//尾
    private int[] arr;//存放数据

    public ArrayQueueDemo(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
        front=-1;//指向队列头前一个位置
        rear=-1;//指向队列尾
    }

    public void addQueue(int n){
        if(full()){
            System.err.println("满");
            return;
        }
        rear++;
        arr[rear]=n;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("空队列");
        }
        front++;
        return arr[front];
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("空队列");
        }
        return arr[front+1];
    }

    public void display(){
        if(isEmpty()){
            System.err.println("空");
            return;
        }
        for (int i = front+1; i <= rear; i++) {
            System.err.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    public boolean full(){
        return rear==maxSize-1;
    }

    public boolean isEmpty(){
        return rear==front;
    }
}

/**
 * 模拟环形队列
 * 重复使用
 * 通过取模来维持下标循环
 * 真是容量是maxsize-1
 */
class ArrayQueueDemo1{
    private int maxSize;//最大容量
    private int front;//头
    private int rear;//指向最后一个位置的后一个位置,
    private int[] arr;//存放数据

    public ArrayQueueDemo1(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }

    public void addQueue(int n){
        if(full()){
            System.err.println("满");
            return;
        }
        arr[rear]=n;
        //1%3=1 rear0->1
        //2%3=2 rear1->2
        //3%3=0 rear2->0
        rear=(rear+1)%maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("空队列");
        }
        int value=arr[front];
        //1%3=1
        //2%3=2
        //3%3=0
        front=(front+1)%maxSize;
        return value;
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("空队列");
        }
        return arr[front];
    }

    public void display(){
        if(isEmpty()){
            System.err.println("空");
            return;
        }
        for (int i = front; i < front+size(); i++) {
            System.err.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    /**
     * 真实容量
     * @return
     */
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    public boolean full(){
        //size为3,只有2个位置可以放置
        //0,1,2  (2+1)%3=0满
        //1,2,3  (3+1)%3=1满
        //2,3,4  (4+1)%3=2满
        return (rear+1)%maxSize==front;
    }

    public boolean isEmpty(){
        return rear==front;
    }
}