package org.bougainvilleas.base.dsaa.tree.huffman;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈扶曼树
 * wpl最小的树即是huffmanTree
 * wpl: Weighted Path Length of Tree
 * 树的所有叶结点的带权路径长度之和，称为树的带权路径长度表示为WPL
 *
 * 第一层     1
 * 第二层   2   3
 * 第三层  4 5 6 7
 * 第四层 8 9
 * eg：8
 * 权=结点值=8
 * 路径=根结点到该结点的深度=层数-1=4-1=3
 * 带权路径=权×路径=8×3=24

 * eg:
 * wpl=8×3+9×3+5×2+6×2+7×2=87
 *

 *
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int a[]={13,7,8,3,29,6,1};
        /**
         * 最终树结构
         *         67
         * 29                 38
         *            15             23
         *          7   8       10        13
         *                   4     6
         *                 1   3
         */
        preOrder(createHuffmanTree(a));
        System.err.println("前序遍历: 67,29,38,15,7,8,23,10,4,1,3,6,13");
    }

    public static void preOrder(Node root){
        if(root==null){
            System.err.println("空树");
        }

        root.postOrder();
    }

    /**
     * 创建huffmanTree
     * @param a
     * @return
     */
    public static Node createHuffmanTree(int a[]){
        List<Node> nodeLis=new ArrayList<>();
        for (int value:a) {
            nodeLis.add(new Node(value));
        }
        while (nodeLis.size()>1){

            //从小到大
            Collections.sort(nodeLis);
            System.err.println(nodeLis);

            //取出权值最小的结点
            Node leftNode=nodeLis.get(0);
            //取出权值次小的结点
            Node rightNode=nodeLis.get(1);

            Node parent=new Node(leftNode.value+ rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;

            //从集合中删除处理过的结点
            nodeLis.remove(leftNode);
            nodeLis.remove(rightNode);
            nodeLis.add(parent);
        }
        return nodeLis.get(0);
    }

}

@ToString(exclude = {"left","right"})
class Node implements Comparable<Node>{
    int value;//权
    Node left;
    Node right;

    //前序遍历
    public void postOrder(){
        System.err.println(this);
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
    }


    public Node(int value) {
        this.value = value;
    }

    /**
     *
     * For the mathematically inclined, the relation that defines the natural ordering on a given class C is:
     *          {(x, y) such that x.compareTo(y) <= 0}.
     * The quotient for this total order is:
     *          {(x, y) such that x.compareTo(y) == 0}.
     *
     * @param o 待比较的对象
     * @return 负数this小，正数this大，0相等
     */
    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.value-o.value;
        //从大到小
//        return o.value-this.value;
    }
}
