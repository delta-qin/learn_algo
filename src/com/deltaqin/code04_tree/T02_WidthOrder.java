package com.deltaqin.code04_tree;

import java.util.LinkedList;

/**
 * @author deltaqin
 * @date 2021/3/5
 */
public class T02_WidthOrder {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int v )    {
            value = v;
        }
    }

    public static void widthOrder(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            if (poll.left != null){
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
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
        root.right.left.left = new Node(6);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(11);

        widthOrder(root);

    }
}
