package org.bougainvilleas.base.dsaa;

import lombok.Data;
import lombok.ToString;

public class DoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedListDemo listDemo = new DoubleLinkedListDemo();
        listDemo.show();
        System.err.println("-----------");
        listDemo.add(new HeroNode2(1, "song"));
        listDemo.add(new HeroNode2(2, "lin"));
        listDemo.add(new HeroNode2(3, "song"));
        listDemo.show();
        System.err.println("-----------");
        listDemo.update(new HeroNode2(1, "songjiang"));
        listDemo.update(new HeroNode2(3, "songjiang"));
        listDemo.show();
        System.err.println("-----------");
        listDemo.delete(new HeroNode2(3, "songjiang"));
        listDemo.show();
        System.err.println("-----------");
        listDemo.addByNo(new HeroNode2(5, "songjiang"));
        listDemo.addByNo(new HeroNode2(3, "songjiang"));
        listDemo.show();


    }
}

class DoubleLinkedListDemo{
    private HeroNode2 head = new HeroNode2(0, "");

    public HeroNode2 getHead(){
        return head;
    }


    /**
     * 有序add，按顺序添加，定位到添加位置的前一个节点
     */
    public void addByNo(HeroNode2 heroNode) {

        HeroNode2 temp = head;
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
            //注意下面语句的顺序
            heroNode.next = temp.next;
            heroNode.pre=temp;
            if(temp.next!=null){//末尾结点防止空指针
                temp.next.pre=heroNode;
            }
            temp.next = heroNode;
        }

    }


    /**
     * 删除，定位到要删除节点的前一个节点
     */
    public void delete(HeroNode2 target) {
        //是否为空
        if (head.next == null) {
            System.err.println("空表");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//下一个节点为空则遍历结束
            }
            if (temp.no == target.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next=temp.next;
            if(temp.next!=null){//不是最后一个结点
                temp.next.pre=temp.pre;
            }
        } else {
            System.err.println("未找到" + target.no + "节点");
        }

    }

    /**
     * 修改，定位到要修改的节点
     */
    public void update(HeroNode2 newHeroNode) {
        //是否为空
        if (head.next == null) {
            System.err.println("空表");
            return;
        }
        HeroNode2 temp = head.next;
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
     * 无序add，定位到 末尾节点
     */
    public void add(HeroNode2 heroNode) {
        //temp 辅助指针，遍历链表
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre=temp;
    }

    /**
     * 遍历
     */
    public void show() {
        //是否为空表
        if (head.next == null) {
            System.err.println("空表");
            return;
        }
        //temp 辅助指针，遍历链表
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;//遍历完链表
            }
            System.err.println(temp);
            temp = temp.next;
        }
    }

}


@ToString(exclude = {"next","pre"})
@Data
class HeroNode2{
    public int no;
    public String name;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }
}