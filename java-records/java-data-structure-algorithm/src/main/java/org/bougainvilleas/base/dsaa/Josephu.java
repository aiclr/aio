package org.bougainvilleas.base.dsaa;

import lombok.ToString;

/**
 * 约瑟夫环
 */
public class Josephu {

    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();

        list.show();

        list.add(new Boy(1));
        list.add(new Boy(2));
        list.add(new Boy(3));
        list.add(new Boy(4));
        list.add(new Boy(5));
        list.show();
        list.josephu(1,3);
    }
}

/**
 * 单向环型链表
 */
class CircleSingleLinkedList {

    private Boy first = new Boy(-1);

    /**
     * 约瑟夫环出圈
     * @param startNo 从第几位开始数
     * @param no 数到该数字出圈
     */
    public void josephu(int startNo,int no) {
        //first的前一个位置
        Boy temp = this.first;
        while (true){
            if(temp.next==first){
                break;
            }
            temp=temp.next;
        }
        //先找开始数数位置
        for (int i = 1; i < startNo; i++) {
            first=first.next;
            temp=temp.next;
        }
        //开始报数
        while (temp!=first){

            for (int i = 0; i < no-1; i++) {
                first=first.next;
                temp=temp.next;
            }
            System.err.println(first);
            temp.next=first.next;
            first=first.next;
        }
        System.err.println(first);
    }

    /**
     * 遍历打印
     */
    public void show() {
        Boy cur = first;
        if(cur.next==null){
            return;
        }
        while (true) {
            System.err.println(cur);
            if (first==cur.next) {
                break;
            }
            cur = cur.next;
        }

    }


    /**
     * 添加
     */
    public void add(Boy newBoy) {
        Boy cur = first;

        if(cur.next==null){
            this.first=newBoy;
            newBoy.next=newBoy;
            return;
        }
        while (true) {
            if (first == cur.next) {
                break;
            }
            cur = cur.next;
        }
        newBoy.next = cur.next;
        cur.next = newBoy;
    }


}


@ToString(exclude = "next")
class Boy {
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }
}
