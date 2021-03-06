package com.deltaqin.code04_tree;

/**
 * @author deltaqin
 * @date 2021/3/6 8:54 上午
 */
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
        root.right.left.left = new Node(6);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(11);
        System.out.println(isAvl(root));

    }
}
