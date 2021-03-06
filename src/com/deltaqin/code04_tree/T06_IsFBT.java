package com.deltaqin.code04_tree;

/**
 * @author deltaqin
 * @date 2021/3/6 8:50 上午
 */
// 使用树型DP的套路解题，可以向左树要信息以及向右树要信息来解题，一般是后序遍历的递归
public class T06_IsFBT {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node (int v){
            value = v;
        }
    }

    public static class ReturnVal {
        public int height;
        public int nodesNum;

        public ReturnVal(int h, int n) {
            height = h;
            nodesNum = n;
        }
    }

    public static boolean isFbt(Node root) {

        if (root == null) return true;
        ReturnVal val = process(root);
//        2 的 val.height 次方 -1
//        return val.nodesNum == (1 << val.height - 1);  错误
        return val.nodesNum == (1 << val.height) - 1;

    }

    public static ReturnVal process(Node root) {
//忘记了递归的结束条件
        if (root == null) return new ReturnVal(0 , 0);

//      分别向左树和右树要信息
        ReturnVal left = process(root.left);
        ReturnVal right = process(root.right);

//        后序遍历
        int height = Math.max(left.height, right.height) + 1;
        int nodeNum = left.nodesNum + right.nodesNum + 1;
        return new ReturnVal(height,nodeNum);
    }

    public static void main(String[] args) {

        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(2);
//        root.left.right = new Node(4);
//        root.right.left = new Node(7);
//        root.right.right = new Node(10);
//        root.left.left.left = new Node(1);
//        root.left.left.right = new Node(1);
//        root.right.left.left = new Node(6);
//        root.right.right.left = new Node(9);
//        root.right.right.right = new Node(11);
        System.out.println(isFbt(root));

    }
}


