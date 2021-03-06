package com.deltaqin.code04_tree;

/**
 * @author deltaqin
 * @date 2021/3/6 8:54 上午
 */
//使用树型DP的套路解题，可以向左树要信息以及向右树要信息来解题，一般是后序遍历的递归
//    成立条件：：左子树是AVL，右子树是AVL，且高度差是1、
//      递归函数返回的是 自己是否是AVL以及自己的高度（两个子树的最大高度+1）
public class T07_isAVL {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node (int v){
            value = v;
        }
    }

    public static boolean isAvl(Node root) {

//        最好将其余操作和递归操作分开

        return process(root).isAVL;
    }

    public static class ReturnInfo{
        public int height;
        public boolean isAVL;

        public ReturnInfo(boolean i, int h) {
            isAVL = i;
            height = h;
        }
    }

    public static ReturnInfo process(Node root) {

//        递归的结束条件
        if (root  == null) {
            return new ReturnInfo(true, 0);
        }
//        后序遍历
        ReturnInfo left = process(root.left);
        ReturnInfo right = process(root.right);

//        计算当前树的高度
        int height = Math.max(left.height, right.height) + 1;
//        判断当前树是否是AVL， 是满足三个条件就可以
        boolean isAVL = left.isAVL && right.isAVL &&
                Math.abs(left.height-right.height) <= 1;
//        递归函数返回的是当前树自己的信息
        return new ReturnInfo(isAVL, height);
    }

    public static void main(String[] args) {

        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(2);
//        root.left.right = new Node(4);
//        root.right.left = new Node(7);
//        root.right.right = new Node(10);
        root.left.left.left = new Node(1);
//        root.left.left.right = new Node(1);
//        root.right.left.left = new Node(6);
//        root.right.right.left = new Node(9);
//        root.right.right.right = new Node(11);
        System.out.println(isAvl(root));

    }
}
