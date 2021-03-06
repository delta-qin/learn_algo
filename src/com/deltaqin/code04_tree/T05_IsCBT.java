package com.deltaqin.code04_tree;

import java.util.LinkedList;

/**
 * @author deltaqin
 * @date 2021/3/6 8:40 上午
 */

//只要是完全二叉树，当宽度优先遍历出现一个节点只有左节点没有右节点的时候，之后的所有节点都没有子节点，否则都不是CBT
public class T05_IsCBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node (int v){
            value = v;
        }
    }

    public static boolean isCbt(Node root) {

        if (root == null) return true;

//        标志没有孩子的节点是否应该出现了
        boolean flag = false;
//        队列实现宽度优先遍历
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            root = linkedList.poll();
//            弹出来就对当前做一些事情，不做就没了
            if (root.left != null && root.right != null) {
                linkedList.add(root.left);
                linkedList.add(root.right);
            }
//            只能出现叶子节点的时候出现了非叶子节点，不是CBT
            if (flag && (root.left != null || root.right != null)) {
                return false;
            }

//            只有右子没有左子不是CBT
            if (root.left == null && root.right != null) {
                return false;
            }
//            第一个叶子节点出现，前面都是有左右子节点
            if (root.left == null && !flag) {
                flag = true;
            }

//            下一个就会出现叶子节点
            if (root.left != null && root.right == null && !flag) {
                linkedList.add(root.left);
                flag = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.right.left = new Node(7);
        root.right.right = new Node(10);
        root.left.left.left = new Node(1);
        root.left.left.right = new Node(1);
//        root.right.left.left = new Node(6);
//        root.right.right.left = new Node(9);
//        root.right.right.right = new Node(11);
        System.out.println(isCbt(root));

    }
}
