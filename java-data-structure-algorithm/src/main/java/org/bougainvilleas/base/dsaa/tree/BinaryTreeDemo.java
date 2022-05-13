package org.bougainvilleas.base.dsaa.tree;

import lombok.ToString;

/**
 * 二叉树
 * 前中后遍历---看父结点输出位置
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
        /**
         *      0
         *   1     2
         * 3  4  5   6
         */
        System.err.println("0134256");
        binaryTree.pre();
        System.err.println("3140526");
        binaryTree.mid();
        System.err.println("3415620");
        binaryTree.post();
        System.err.println("---");

        System.err.println(binaryTree.find(5, 0));
        System.err.println(binaryTree.find(5, 1));
        System.err.println(binaryTree.find(5, 2));

        binaryTree.del(4);
        System.err.println("0134256");
        binaryTree.pre();

    }
}

class BinaryTree {

    public HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
