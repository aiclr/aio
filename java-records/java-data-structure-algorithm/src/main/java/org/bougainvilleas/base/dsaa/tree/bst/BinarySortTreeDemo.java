package org.bougainvilleas.base.dsaa.tree.bst;

import lombok.ToString;

/**
 * 二叉排序树
 *
 * 尽量避免相同值
 *
 * 中序遍历的结果是有序数组
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int a[] = {7, 3, 10, 12, 5, 1, 9,12,2};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < a.length; i++) {
            tree.add(new Node(a[i]));
        }
        tree.midOrder();
        System.err.println("1,3,5,7,9,10,12");

        //叶子
        tree.del(2);
        tree.del(1);
        System.err.println("删除叶子1");
        tree.midOrder();
        //一个子结点
        tree.del(12);
        System.err.println("删除结点12");
        tree.midOrder();
        //两个子结点
        tree.del(3);
        tree.del(10);
        System.err.println("删除结点3和10");
        tree.midOrder();

        tree.del(7);
        tree.del(9);
        tree.del(12);
        tree.del(5);
        tree.midOrder();
    }
}

class BinarySortTree {
    private Node root;

    public void del(int val){
        if(root==null){
            return;
        }else {
            Node targetNode=search(val);
            //没找到
            if(targetNode==null){
                return;
            }
            // 只有一个跟结点，且是目标结点，直接将root置空
            // 等价于没有父结点，不需要查找父结点，直接return
            // 后面删除含有单结点的逻辑，还要考虑是否是root结点，88和99行
            if (root.left==null&&root.right==null){
                root=null;
                return;
            }
            //父结点
            Node parent=searchParent(val);
            //要删除的是叶子结点
            if(targetNode.left==null&&targetNode.right==null){
                //判断要删除的是父结点的左子结点还是右子结点
                if(parent.left!=null&&parent.left.val==val){
                    parent.left=null;
                }else if(parent.right!=null&&parent.right.val==val){
                    parent.right=null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){
                //删除有两个子树的结点---递归
                // 如果想将当前结点的右子树部分提到被删除位置，
                // 则需要找到右子树里的小值，以保证右侧都大于新结点的值（左侧肯定都是小于右侧的）
                int minVal=delRightTreeMin(targetNode.right);
                targetNode.val=minVal;
                // 如果想将当前结点的左子树部分提到被删除位置，
                // 则需要找到左子树里的大值，以保证左侧都小于新结点的值（右侧肯定都是大于左侧的）
//                int maxVal=delLeftTreeMax(targetNode.left);
//                targetNode.val=maxVal;
            }else {
                //有左子树
                if(targetNode.left!=null){
                    if(parent!=null){
                        //判断要删除的是父结点的左子结点还是右子结点
                        if(parent.left!=null&&parent.left.val==val){
                            parent.left=targetNode.left;
                        }else if(parent.right!=null&&parent.right.val==val){
                            parent.right=targetNode.left;
                        }
                    }else {
                        root=targetNode.left;
                    }
                }else {
                    if(parent!=null){
                        //判断要删除的是父结点的左子结点还是右子结点
                        if(parent.left!=null&&parent.left.val==val){
                            parent.left=targetNode.right;
                        }else if(parent.right!=null&&parent.right.val==val){
                            parent.right=targetNode.right;
                        }
                    }else {
                        root=targetNode.right;
                    }
                }
            }


        }

    }

    /**
     * 返回以node为根结点的二叉排序树的最小结点的  值
     * 删除node为跟结点的二叉排序树的最小结点
     *
     * @param node 传入的结点
     * @return 返回以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node){
        Node target=node;
        //循环左结点，找最小值
        while (target.left!=null){
            target=target.left;
        }
        //删除最小结点
        del(target.val);
        return target.val;
    }
    /**
     * 返回以node为根结点的二叉排序树的最大结点的  值
     * 删除node为跟结点的二叉排序树的最大结点
     *
     * @param node 传入的结点
     * @return 返回以node为根结点的二叉排序树的最大结点的值
     */
    public int delLeftTreeMax(Node node){
        Node target=node;
        //循环左结点，找最小值
        while (target.right!=null){
            target=target.right;
        }
        //删除最小结点
        del(target.val);
        return target.val;
    }


    //查删除结点
    public Node search(int val){
        if (root == null) {
            return null;
        }else {
            return root.search(val);
        }
    }

    //查父结点
    public Node searchParent(int val){
        if (root == null) {
            return null;
        }else {
            return root.searchParent(val);
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }else {
            System.err.println("空树");
        }
    }

}


@ToString(exclude = {"left", "right"})
class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    /**
     * @param value 希望删除的结点的值
     * @return 找到返回该结点，否则返回null
     */
    public Node search(int value) {
        //找到
        if (value == this.val) {
            return this;
        } else if (value < this.val) {//查找的值，小于当前结点的值，应该往左子树递归
            //如果左子树为空则返回
            if (this.left == null) {
                return null;
            }
            //左子树不为null则递归
            return this.left.search(value);
        } else {//查找的值，大于当前结点的值，应该往右子树递归
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * @param value 要查找的值
     * @return 要删除的结点的父结点，没有返回null
     */
    public Node searchParent(int value) {

        if ((this.left != null && this.left.val == value)
           ||
           (this.right != null && this.right.val == value))
        {
            return this;
        }else {
            //小于当前结点的值，并且左子结点不为null，则往左递归
            if(value<this.val&&this.left!=null){
                return this.left.searchParent(value);
            }else if(value>this.val&&this.right!=null){
                return this.right.searchParent(value);
            }return null;//没有父结点
        }
    }


    /**
     * 从root结点开始，递归
     *
     * @param n 待插入数据
     */
    public void add(Node n) {
        if (n == null) {
            return;
        }
        //待插入数小于当前结点，应该往左子树方向
        if (n.val < this.val) {
            if (this.left == null) {
                this.left = n;
            } else {
                //往左递归
                this.left.add(n);
            }
        } else {//待插入数大于当前结点，应该往右子树方向
            if (this.right == null) {
                this.right = n;
            } else {
                //往右递归
                this.right.add(n);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.err.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

}
