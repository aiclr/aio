package org.bougainvilleas.base.dsaa.tree.avl;

import lombok.Getter;
import lombok.ToString;

/**
 * 平衡二叉搜索树 AVLTree
 * 特征：
 *   它是一棵空树，或者它的左右两个子树的深度差的绝对值不超过1
 *   并且左右两棵子树都是一棵平衡二叉搜索树
 *   常用的实现方法有：红黑树，AVL,替罪羊树，Treap,伸展树
 *   eg
 *        1
 *   2        3
 * 4   5
 * 左深度=2,右深度=1,差1是平衡二叉搜索树
 *            1
 *       2        3
 *     4   5
 *   6
 * 左深度=3,右深度=1,差2，不是平衡二叉搜索树
 *
 *
 * 意义：
 * 为了优化二叉排序树的查询效率
 * eg {1,2,3,4,5}
 * 生成的二叉排序树是
 * 1
 *   2
 *     3
 *       4
 *         5
 * 这样的排序树更像是单链表，
 * 插入速度不影响，
 * 查询速度还没单链表快（比单链表多了判断左子树是否存在的逻辑），所以要使用平衡二叉排序树提升
 *
 *
 * 创建平衡二叉搜索树
 * 1）先创建BinarySortTree
 * 2）然后判断是否是平衡二叉排序树
 *   2.1）左子树的深度<右子树的深度----左旋转：将左子树的深度增加（可参考ipad上的图解）
 *       2.1.1）先创建一个临时结点temp=当前结点的值（root.value）
 *       2.1.2）temp的左子树=当前结点的左子树（root.left）
 *       2.1.3）temp的右子树=当前结点的右子树的左子树（root.right.left）
 *       2.1.4）把当前结点的值换为当前结点的右子结点的值,(root.value=root.right.value)
 *       2.1.5) 把当前结点的右子树设置为当前结点的右子树的右子树（root.right=root.right.right）
 *       2.1.6）把当前结点的左子树设置为临时结点(root.left=temp)
 *   2.2）右子树的深度<左子树的深度----右旋转：将右子树的深度增加
 *
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //右子树深度》左子树,单次旋转即可
//        int[] a={4,3,6,5,7,8};
        //左子树高度》右子树 单次旋转即可
//        int[] a={10,12,8,9,7,6};
        //需要多次旋转的时候，== 双旋转
        int[] a={10,7,11,6,8,9};
        AVLTree avlTree=new AVLTree();
        for (int i = 0; i < a.length; i++) {
            avlTree.add(new Node(a[i]));
        }



        System.err.println("中序遍历");
        avlTree.midOrder();
        System.err.printf("根结点深度为%d\n",avlTree.getRoot().getDeep());
        System.err.printf("左子树深度为%d\n",avlTree.getRoot().left.getDeep());
        System.err.printf("右子树深度为%d\n",avlTree.getRoot().right.getDeep());
        System.err.println("根结点是 "+avlTree.getRoot());

    }


}

@Getter
class AVLTree{
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
     * 左旋转  此方法应该设置为private 在添加结点的时候使用
     *   2.1）左子树的深度<右子树的深度----左旋转：将左子树的深度增加（可参考ipad上的图解）
     *       2.1.1）先创建一个临时结点temp=当前结点的值（root.value）
     *       2.1.2）temp的左子树=当前结点的左子树（root.left）
     *       2.1.3）temp的右子树=当前结点的右子树的左子树（root.right.left）
     *       2.1.4）把当前结点的值换为当前结点的右子结点的值,(root.value=root.right.value)
     *       2.1.5) 把当前结点的右子树设置为当前结点的右子树的右子树（root.right=root.right.right）
     *       2.1.6）把当前结点的左子树设置为临时结点(root.left=temp)
     */
    private void leftRotate(){
        Node temp = new Node(val);
        temp.left=left;
        temp.right=right.left;
        val=right.val;
        right=right.right;
        left=temp;
        System.err.println("左旋转");

    }

    /**
     * 右旋转
     */
    private void rightRotate(){
        Node temp=new Node(val);
        temp.right=right;
        temp.left=left.right;
        val=left.val;
        left=left.left;
        right=temp;
        System.err.println("右旋转");
    }

    /**
     * @return 左子树的深度
     */
    public int getLeftDeep() {
        if (left == null) {
            return 0;
        }
        return left.getDeep();
    }

    /**
     * @return 右子树的深度
     */
    public int getRightDeep() {
        if (right == null) {
            return 0;
        }
        return right.getDeep();
    }


    /**
     * @return 返回当前结点的深度
     */
    public int getDeep() {
        return Math.max(left == null ? 0 : left.getDeep(), right == null ? 0 : right.getDeep()) + 1;
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
                (this.right != null && this.right.val == value)) {
            return this;
        } else {
            //小于当前结点的值，并且左子结点不为null，则往左递归
            if (value < this.val && this.left != null) {
                return this.left.searchParent(value);
            } else if (value > this.val && this.right != null) {
                return this.right.searchParent(value);
            }
            return null;//没有父结点
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
        //当添加完一个结点后，如果右子树深度-左子树深度>1,即当前二叉排序树不平衡，左旋转将BST转换为AVL
        if(getRightDeep()-getLeftDeep()>1){
            //参考ipad笔记
            // 当整体需要往左旋转时，（右侧树更深，此时需要判断一下右子树的右子树和右子树的左子树的深度）
            // 如果当前结点的右子树的左子树深度大于右子树的右子树深度，
            // 先把右子树进行一个右旋转，将右子树转换成，右子树的右子树深度>右子树的左子树深度（等于不需要考虑，无意义）
            // 再对当前结点进行左旋转
            if(right!=null&&right.getRightDeep()<right.getLeftDeep()){
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
            //此处return,就不会再走下面的逻辑了
            return;
        }
        if (getLeftDeep()-getRightDeep()>1) {
            // 当整体需要往右旋转时，（左侧树更深，此时需要判断一下左子树的左子树和左子树的右子树的深度）
            // 如果当前结点的左子树的右子树深度大于左子树的左子树深度，
            // 先把左子树进行一个左旋转，将左子树转换成，左子树的左子树深度>左子树的右子树深度（等于不需要考虑，无意义）
            // 再对当前结点进行右旋转
            if(left!=null&&left.getRightDeep()>left.getLeftDeep()){
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
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