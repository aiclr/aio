package org.bougainvilleas.base.dsaa.tree;

/**
 * 数组存储二叉树
 *
 * n为下标，
 * 左子树2n+1
 * 右子树2n+2
 * 父结点（n-1)/2
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int a[]={0,1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(null);
        arrBinaryTree.preOrder(0);
    }
}

class ArrBinaryTree{
    int [] a;

    public ArrBinaryTree(int[] a) {
        this.a = a;
    }

    public void preOrder(int index){
        if(a==null||a.length==0){
            System.err.println("空数组");
            return;
        }
        System.err.println(a[index]);
        if((2*index+1)<a.length){
            preOrder(2*index+1);
        }
        if((2*index+2)<a.length){
            preOrder(2*index+2);
        }
    }
}