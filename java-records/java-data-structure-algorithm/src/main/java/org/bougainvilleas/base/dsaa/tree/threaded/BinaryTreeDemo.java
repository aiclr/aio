package org.bougainvilleas.base.dsaa.tree.threaded;

import lombok.ToString;

/**
 * 线索化二叉树
 * 中序线索化
 * 按照中序遍历顺序，扩展二叉树，
 * 线索化前
 * 0
 * 1     2
 * 3  4  5   6
 * 中序遍历顺序=3140526
 * 3的前驱是空，左子树不用处理，后继是1,判断3右子树，为空则将3的右子树指向1,设置3的右子树类型为后继结点=1
 * 1号，前驱3后继4,但是1号左右子树都不为空，则不处理（此时左右子树类型为左右子树，默认值0）
 * 4号，前驱1,后继0，4号左右子树都为空，所以左右子树类型都设置为结点=1，左子树指向前驱1，右子树指向后继0
 * 0类似1
 * 5类似4
 * 2类似1
 * 6类是4
 * 线索化后二叉树扩展为
 * 0
 * 1           2
 * 3    4     5      6
 * 1 1  0  0   2  2
 * TODO 实现前序、后序线索化二叉树
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode king = new HeroNode(0, "king");
        HeroNode caddy = new HeroNode(1, "caddy");
        HeroNode jack = new HeroNode(2, "jack");
        HeroNode lync = new HeroNode(3, "lync");
        HeroNode bob = new HeroNode(4, "bob");
        HeroNode tom = new HeroNode(5, "tom");
        HeroNode jerry = new HeroNode(6, "jerry");
        king.left = caddy;
        king.right = jack;
        caddy.left = lync;
        caddy.right = bob;
        jack.left = tom;
        jack.right = jerry;
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(king);
        System.err.println("3140526");
        binaryTree.mid();
        /**
         * 线索化前
         *      0
         *   1     2
         * 3  4  5   6
         */
        binaryTree.threadedNodes();
        /**
         * 线索化后
         *           0
         *     1           2
         *  3    4     5      6
         *   1 1  0  0   2  2
         *
         */
        System.err.println("线索化后序遍历");
        binaryTree.threadedShow();
        System.err.println("根结点");
        System.err.println(king);
        System.err.println(king.left);
        System.err.println(king.right);
        System.err.println(king.rightType);

        System.err.println(lync);
        System.err.println(lync.left);
        System.err.println(lync.right);
        System.err.println(lync.rightType);

        System.err.println(bob);
        System.err.println(bob.left);
        System.err.println(bob.right);
        System.err.println(bob.rightType);

        System.err.println(tom);
        System.err.println(tom.left);
        System.err.println(tom.right);
        System.err.println(tom.rightType);

        System.err.println(jerry);
        System.err.println(jerry.left);
        System.err.println(jerry.right);
        System.err.println(jerry.rightType);
    }
}

/**
 * 线索化二叉树
 */
class BinaryTree {

    public HeroNode root;

    //为了实现线索化，创建当前结点的前驱结点，辅助指针
    //递归线索化时，pre始终是前一个结点
    public HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 中序遍历线索化二叉树
     *           0
     *     1           2
     *  3    4     5      6
     *   1 1  0  0   2  2
     */
    public void threadedShow() {
        HeroNode node = root;
        while (node != null) {
            //找左子树类型为1的，0->1->3
            while (node.leftType == 0) {
                node=node.left;
            }
            System.err.println(node);
            //找右指针是否是后继结点
            while (node.rightType==1){
                node=node.right;
                System.err.println(node);
            }
            node=node.right;
        }
    }

    /**
     * 重载
     */
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 中序线索化
     *
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node) {
        //null不线索化
        if (node == null) {
            return;
        }
        //先线索化左子树
        threadedNodes(node.left);
        /**
         *  再线索化当前结点
         *  先处理前驱结点
         */
        //前驱结点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        //后继结点
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //再线索化右子树
        threadedNodes(node.right);


    }


    public void del(int id) {
        if (root != null) {
            if (root.id == id) {
                root = null;
            } else {
                root.del(id);
            }
        } else {
            System.err.println("空树");
        }
    }

    public HeroNode find(int id, int mark) {
        if (root != null) {
            switch (mark) {
                case 0:
                    return root.preFind(id);
                case 1:
                    return root.midFind(id);
                default:
                    return root.postFind(id);
            }
        }
        return null;
    }

    public void pre() {
        if (root != null) {
            root.preOrder();
        }
    }

    public void mid() {
        if (root != null) {
            root.midOrder();
        }
    }

    public void post() {
        if (root != null) {
            root.postOrder();
        }
    }
}


/**
 * 结点
 */
@ToString(exclude = {"left", "right"})
class HeroNode {
    public int id;
    public String name;
    public HeroNode left;
    public HeroNode right;

    //leftType=0 左子树 1前驱结点
    public int rightType;
    //rightType=0 右子树 1后继结点
    public int leftType;


    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 递归删除
     *
     * @param id
     */
    public void del(int id) {
        /*
        //写法1 从最左，到最右
        if (this.left != null) {
            if (this.left.id == id) {
                this.left = null;
                return;
            } else {
                this.left.del(id);
            }
        }
        if (this.right != null) {
            if (this.right.id == id) {
                this.right = null;
            } else {
                this.right.del(id);
            }
        }
        */

        //写法2，一层一层，递归
        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.del(id);
        }
        if (this.right != null) {
            this.right.del(id);
        }
    }


    public HeroNode preFind(int id) {
        System.err.println("pre");
        if (this.id == id) {
            return this;
        }
        HeroNode result = null;
        if (this.left != null) {
            result = this.left.preFind(id);
        }
        if (result != null) {
            return result;
        }
        if (this.right != null) {
            result = this.right.preFind(id);
        }
        return result;
    }


    public HeroNode midFind(int id) {
        HeroNode result = null;
        if (this.left != null) {
            result = this.left.midFind(id);
        }
        if (result != null) {
            return result;
        }
        System.err.println("mid");
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            result = this.right.midFind(id);
        }
        return result;
    }

    public HeroNode postFind(int id) {
        HeroNode result = null;
        if (this.left != null) {
            result = this.left.postFind(id);
        }
        if (result != null) {
            return result;
        }
        if (this.right != null) {
            result = this.right.postFind(id);
        }
        if (result != null) {
            return result;
        }
        System.err.println("post");
        if (this.id == id) {
            return this;
        }
        return result;
    }

    /**
     * 前序遍历
     * 先输出当前结点，再输出左结点，再输出右结点
     */
    public void preOrder() {
        System.err.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     * 先输出左结点，再输出当前结点，再输出右结点
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

    /**
     * 后序遍历
     * 先输出左结点，再输出右结点，再输出当前结点
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.err.println(this);
    }

}
