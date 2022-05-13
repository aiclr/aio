package org.bougainvilleas.base.dsaa.hashtab;

import lombok.ToString;

/**
 * 哈希表
 *  数组+链表
 *  数组+二叉树
 */
public class MyHashTab {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        Emp caddy = new Emp(1, "caddy");
        Emp caddy2 = new Emp(15, "caddy2");
        Emp jack = new Emp(8, "jack");
        hashTab.add(caddy);
        hashTab.add(caddy2);
        hashTab.add(jack);
        hashTab.show();
        System.err.println(hashTab.find(2));
        System.err.println(hashTab.find(1));
        System.err.println(hashTab.find(8));
        hashTab.del(1);
        hashTab.del(8);
        hashTab.del(2);
        hashTab.show();

    }
}

@ToString(exclude = {"next"})
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void show(int no) {
        if (head == null) {
            System.err.println("第" + no + "条链表为空");
            return;
        }
        System.err.printf("第%d条链表内容为:", no);
        Emp curEmp = head;
        while (true) {
            System.err.printf("%s\t", curEmp.toString());
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.err.println();
    }

    public Emp find(int id) {
        if (head == null) {
            return null;
        }
        Emp temp = head;
        while (true) {
            if (id == temp.id) {
                break;
            }
            if (temp.next == null) {
                temp=null;
                break;
            }
            temp=temp.next;
        }
        return temp;
    }

    public void del(int id) {
        if (head == null) {
            return;
        }
        if(head.id==id){
            head=head.next;
            return;
        }
        Emp temp = head;
        while (true) {
            if(temp.next.id==id){
                temp.next=temp.next.next;
                break;
            }
            if(temp.next.next==null){
                break;
            }
            temp=temp.next;
        }
    }
}

class HashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedLists[empLinkedListNo].add(emp);
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].show(i);
        }
    }

    public Emp find(int id) {
        return empLinkedLists[hashFun(id)].find(id);
    }

    public void del(int id){
        empLinkedLists[hashFun(id)].del(id);
    }

    //散列函数
    public int hashFun(int id) {
        return id % size;
    }
}