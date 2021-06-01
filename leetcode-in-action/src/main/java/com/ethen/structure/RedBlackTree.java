package com.ethen.structure;

import lombok.Data;

/**
 * 红黑树实现
 * 红黑树的节点数据结构
 * 左旋 右旋
 * 变色
 * 中序遍历
 * 节点查找
 * 节点插入
 * 节点删除
 * 红黑树平衡操作
 * todo 方法未完待续...
 */
@Data
public class RedBlackTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    // 根节点
    private RBNode<K, V> root;

    /**
     * 根据Key获取Value
     *
     * @param key k
     * @return value
     */
    public V get(K key) {
        if (key == null || this.root == null) return null;
        RBNode<K, V> cursor = this.root;
        while (cursor != null) {
            int cmp = key.compareTo(cursor.key);
            if (cmp == 0) return cursor.getValue();
            if (cmp > 0) cursor = cursor.right;
            else cursor = cursor.left;
        }
        return null;
    }

    // 中序打印红黑树
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    // 递归打印
    private void inOrderPrint(RBNode<K, V> rbNode) {
        if (rbNode == null) return;
        inOrderPrint(rbNode.left);
        String color = rbNode.color == RED ? "RED" : "BLACK";
        System.err.println("key:" + rbNode.key + ",value:" + rbNode.value + ",color:" + color);
        inOrderPrint(rbNode.right);
    }

    /**
     * 红黑树节点数据结构
     *
     * @param <K>
     * @param <V>
     */
    @Data
    static class RBNode<K, V> {
        private RBNode<K, V> parent;
        private RBNode<K, V> left;
        private RBNode<K, V> right;
        private K key;
        private V value;
        private boolean color;
    }
}
