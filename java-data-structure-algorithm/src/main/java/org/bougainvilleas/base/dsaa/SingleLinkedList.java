package org.bougainvilleas.base.dsaa;

import lombok.Data;
import lombok.ToString;

import java.util.Stack;

public class SingleLinkedList {

    public static void main(String[] args) {
        SingleLinkedListDemo listDemo = new SingleLinkedListDemo();
        listDemo.show();
        listDemo.add(new HeroNode(2, "lin"));
        listDemo.add(new HeroNode(1, "song"));
        listDemo.add(new HeroNode(3, "song"));
        listDemo.update(new HeroNode(1, "songjiang"));
        listDemo.update(new HeroNode(3, "songjiang"));
        listDemo.show();

        SingleLinkedListDemo listDemo2 = new SingleLinkedListDemo();
        listDemo2.show();
        System.err.println(listDemo2.getLength());
        listDemo2.addByNo(new HeroNode(2, "lin"));
        listDemo2.addByNo(new HeroNode(1, "song"));
        listDemo2.addByNo(new HeroNode(2, "song2"));
        listDemo2.addByNo(new HeroNode(3, "song3"));
        System.err.println("----");
        System.err.println(listDemo2.findLastNode(2));
        System.err.println(listDemo2.findLastNode(1));
        System.err.println(listDemo2.findLastNode(3));
        System.err.println("----");
        listDemo2.show();
        System.err.println(listDemo2.getLength());
        listDemo2.update(new HeroNode(1, "songjiang"));
        listDemo2.update(new HeroNode(3, "songjiang"));
        listDemo2.show();
        System.err.println(listDemo2.getLength());
        listDemo2.delete(new HeroNode(3, "songjiang"));
        listDemo2.delete(new HeroNode(3, "songjiang"));
        listDemo2.delete(new HeroNode(1, "songjiang"));
        listDemo2.show();
        System.err.println(listDemo2.getLength());

        System.err.println("反转前");


        SingleLinkedListDemo listDemo3 = new SingleLinkedListDemo();
        listDemo3.add(new HeroNode(11, "lin"));
        listDemo3.add(new HeroNode(22, "song"));
        listDemo3.add(new HeroNode(33, "bbb"));
        listDemo3.show();
        System.err.println("反转后");
        listDemo3.reversList();
        listDemo3.show();
        System.err.println("逆序打印");
        listDemo3.stackShow();


    }
}

@ToString(exclude = "next")
@Data
class HeroNode {
    public int no;
    public String name;
    public HeroNode next;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }
}

class SingleLinkedListDemo {
    private HeroNode head = new HeroNode(0, "");

    /**
     * 逆序打印-百度面试  利用栈
     */
    public void stackShow(){
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack=new Stack<>();
        HeroNode cur = head.next;
        while (cur!=null){
            stack.add(cur);
            cur=cur.next;
        }
        while (!stack.empty()){
            System.err.println(stack.pop());
        }   
    }

    /**
     * 链表反转-头插法-腾讯面试
     */
    public void reversList(){
        //空表，或只有一个节点
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode next=null;
        HeroNode newHead = new HeroNode(0, "");

        while (cur!=null){
            next=cur.next;
            cur.next=newHead.next;
            newHead.next=cur;
            cur=next;
        }
        head.next=newHead.next;
    }

    /**
     * 查找单链表中倒数第k个节点-新浪面试题
     */
    public HeroNode findLastNode(int k){
        if(head.next==null){
            return null;
        }
        if(k>getLength()||k<=0){
            throw new IllegalArgumentException();
        }
        HeroNode temp = head.next;//从第一个节点找
        int index=getLength()-k;//倒数第k个节点,指针移动index次
        for (int i = 0; i < index; i++) {
            temp=temp.next;
        }
        return temp;
    }


    /**
     * 获取长度
     */
    public int getLength() {
        int length=0;
        if (head.next == null) {
            return length;
        }
        HeroNode temp=head.next;
        while (temp!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }

    /**
     * 删除，定位到要删除节点的前一个节点
     */
    public void delete(HeroNode target) {
        //是否为空
        if (head.next == null) {
            System.err.println("空表");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;//下一个节点为空则遍历结束
            }
            if (temp.next.no == target.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.err.println("未找到" + target.no + "节点");
        }

    }

    /**
     * 修改，定位到要修改的节点
     */
    public void update(HeroNode newHeroNode) {
        //是否为空
        if (head.next == null) {
            System.err.println("空表");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
        } else {
            System.err.println("未找到" + newHeroNode.no + "节点");
        }
    }


    /**
     * 有序add，按顺序添加，定位到添加位置的前一个节点
     */
    public void addByNo(HeroNode heroNode) {

        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //是否为空表
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.err.print(heroNode);
            System.err.println("已存在");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    /**
     * 无序add，定位到 末尾节点
     */
    public void add(HeroNode heroNode) {
        //temp 辅助指针，遍历链表
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void show() {
        //是否为空表
        if (head.next == null) {
            System.err.println("空表");
            return;
        }
        //temp 辅助指针，遍历链表
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;//遍历完链表
            }
            System.err.println(temp);
            temp = temp.next;
        }
    }
}