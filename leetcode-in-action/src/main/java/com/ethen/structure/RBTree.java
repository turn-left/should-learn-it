package com.ethen.structure;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    //树根
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    /*
     * 获取当前节点的父节点
     * @param node
     * */
    private RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    /*
     * 节点是否为红色
     * @param node
     * */
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    /*
     * 节点是否为黑色
     * @param node
     * */
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /*
     * 设置节点为红色
     * @param node
     * */
    private void setRed(RBNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /*
     * 设置节点为黑色
     * @param node
     * */
    private void setBlack(RBNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /*
     * 中序打印二叉树
     * */
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    //重载
    public void inOrderPrint(RBNode node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key:" + node.key + "value" + node.value);
            inOrderPrint(node.right);
        }

    }

    /*
     * 左旋
     * 1.将x的右子节点指向y的左子节点(LY),y的左子节点的父节点更新为x。
     * 2.当x的父节点(不为空时)，更新y的父节点为x的父节点，并将x的父节点指定子树（当前x的子树位置）指定为y
     * 3.将x的父节点更新为y，将y的左子节点更新为x
     * */
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        //1.将x的右子节点指向y的左子节点(LY),y的左子节点的父节点更新为x。
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        //2.当x的父节点(不为空时)，更新y的父节点为x的父节点，并将x的父节点指定子树（当前x的子树位置）指定为y
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {//说明x为根结点，此时需要更新y为根结点
            this.root = y;
            this.root.parent = null;
        }

        //3.将x的父节点更新为y，将y的左子节点更新为x
        x.parent = y;
        y.left = x;
    }

    /*
     * 右旋
     * 1.将y的左子结点指向y的右子节点，并且更新x的右子节点的父节点为y
     * 2.当y的父节点不为空时，更新x的父节点为y的父节点，更新y的父节点的指定子节点（y当前的位置）为x
     * 3.更新x的父节点为x，然后x的右子节点为y。
     * */
    private void rigthRotate(RBNode y) {
        RBNode x = y.left;
        //1.将y的左子结点指向y的右子节点，并且更新x的右子节点的父节点为y
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        //2.当y的父节点不为空时，更新x的父节点为y的父节点，更新y的父节点的指定子节点（y当前的位置）为x
        if (y.parent != null) {
            x.parent = y.parent;
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        } else {
            this.root = x;
            this.root.parent = null;
        }

        //更新x的父节点为x，然后x的右子节点为y。
        y.parent = x;
        x.right = y;
    }

    /*
     * 公开的插入方法
     * @param key
     * @param value
     * */
    public void insert(K key, V value) {
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        //新节点一定是红色！
        node.setColor(RED);
        insert(node);
    }

    private void insert(RBNode node) {
        //1.查找当前node的父节点
        RBNode parent = null;
        RBNode x = this.root;
        while (x != null) {
            parent = x;
            //cmp>0 说明node.key 大于x.key 需要到x的右子树查找
            //cmp==0 说明node.key 等于x.key 说明需要进行替换
            //cmp < 0 说明node.key 小于x.key 说明需要到x的左子树查找
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp == 0) {
                x.setValue(node.getValue());
                return;
            } else {
                x = x.left;
            }
        }
        node.parent = parent;

        if (parent != null) {
            //判断node与parent的 key 谁大
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {
                //当前node的key比parent的key大，需要把node放入parent的右子节点
                parent.right = node;
            } else {
                //当前node的key比parent的key小，需要把node放入parent的左子节点
                parent.left = node;
            }
        } else {
            this.root = node;
        }
        //需要调用  修复红黑树的平衡的方法
        insertFixUp(node);
    }

    /*
     * 插入后修复红黑树平衡的方法
     * */
    private void insertFixUp(RBNode node) {
        //情况一
        this.root.setColor(BLACK);

        RBNode parent = parentOf(node);
        RBNode gparent = parentOf(parent);
        //情景4：插入父节点为红色
        if (parent != null && isRed(parent)) {
            //如果父节点存在，则一定存在爷爷节点，因为根结点不可能是红色
            RBNode uncle = null;
            if (parent == gparent.left) {
                uncle = gparent.right;
                //情景4.1:叔叔节点存在，并且为红色（父-叔 双红）
                if (uncle != null && isRed(uncle)) {
                    //将爸爸和叔叔染为黑色，爷爷染为红色，并且再以爷爷节点为当前节点，进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }
                //情景4.2：叔叔节点不存在，或者为黑色
                if (uncle == null || isBlack(uncle)) {
                    //情景4.2.1：插入节点为其父节点的左子节点（LL情况），将爸爸染为黑色
                    //爷爷染为红色，然后以爷爷右旋
                    if (node == parent.left) {
                        setBlack(parent);
                        setRed(gparent);
                        rigthRotate(gparent);
                        return;
                    }
                    //情景4.2.2
                    if (node == parent.right) {
                        leftRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            } else {
                uncle = gparent.left;
                //父节点为爷爷 节点的右子树
                if (uncle != null && isRed(uncle)) {
                    //将爸爸和叔叔染为黑色，爷爷染为红色，并且再以爷爷节点为当前节点，进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }
                //情景4.3叔叔节点不存在
                if (uncle == null || isBlack(uncle)) {
                    //4.3.1
                    if (node == parent.right) {
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }
                    //4.3.2
                    if (node == parent.left) {
                        rigthRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            }
        }

    }


    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
