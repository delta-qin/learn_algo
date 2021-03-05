package com.deltaqin.code04_tree;

public class T01_PreInPostOrder {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.left.left.left = new Node(1);
        root.right.left = new Node(7);
        root.right.left.left = new Node(6);
        root.right.right = new Node(10);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(11);

        System.out.println();
    }

}
