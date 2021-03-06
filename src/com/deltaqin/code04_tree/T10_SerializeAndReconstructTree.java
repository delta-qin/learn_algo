package com.deltaqin.code04_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author deltaqin
 * @date 2021/3/6 3:50 下午
 */

// 二叉树的序列化和反序列化
// 就是内存里的一棵树如何变成字符串形式，
// 又如何从字符串形式变成内存里的树
//
// 如何判断一颗二叉树是不是另一棵二叉树的子树？
public class T10_SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

//    ======== 先序 =====================================================
//    先序序列化：中左右
    public static String preOrderSerialize(Node root) {
        if (root == null) return "#~";

        String str = root.value + "~";
        str += preOrderSerialize(root.left);
        str += preOrderSerialize(root.right);

        return str;
    }

//    先序反序列化:
    public static Node  preOrderUnSerialize(String str) {
        String[] strings = str.split("~");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i < strings.length; i++) {
            queue.offer(strings[i]);
        }
//        使用队列恢复树，还是中左右.先序递归
        return revocerPre(queue);
    }

    //        使用队列恢复树，还是中左右
    private static Node revocerPre(Queue<String> queue) {
        String poll = queue.poll();
        if (poll.equals("#")) return null;

        int val = Integer.parseInt(poll);
        Node node = new Node(val);

        node.left = revocerPre(queue);
        node.right = revocerPre(queue);
        return node;
    }

//==========按层序列化===============================================
    public static String serialByLevel(Node root) {
        if (root == null) return "#~";

        String str = root.value + "~";
//        宽度优先是用队列遍历
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.left != null) {
//               注意宽度优先弹出值之后的处理是分情况处理的
//                    有值才压队列，没有就不压但是在字符串里留下占位，恢复的时候才可以分清，遇到特殊字符直接跳过一个节点
                str += poll.left.value + "~";
                queue.offer(poll.left);
            }
//            精华所在，没有的子节点使用特殊字代替
            else {
                str += "#~";
            }

            if (poll.right != null) {
//               注意宽度优先弹出值之后的处理是分情况处理的
//                    有值才压队列，没有就不压但是在字符串里留下占位，恢复的时候才可以分清，遇到特殊字符直接跳过一个节点
                str += poll.right.value + "~";
                queue.offer(poll.right);
            }
//            精华所在，没有的子节点使用特殊字代替
            else {
                str += "#~";
            }
        }
        return str;
    }

//    序列化的时候排队进出队列，反序列的的时候相当于又排队进出队列
    public static Node unserialByLevel(String str){
        String[] strings = str.split("~");
        if (strings[0].equals("#")) return null;
        int index = 0;
        Node root = new Node(Integer.parseInt(strings[index++]));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            String l = strings[index++];
//            字符串居然写出了 !=
            if (!l.equals("#")) {
                Node left = new Node(Integer.parseInt(l));
                poll.left = left;
                queue.offer(left);
            }
            String r = strings[index++];
            if (!r.equals("#")) {
                Node right = new Node(Integer.parseInt(r));
                poll.right = right;
                queue.offer(right);
            }
        }
        return root;
    }

//    测试================================================================
// for test -- print tree
public static void printTree(Node head) {
    System.out.println("Binary Tree:");
    printInOrder(head, 0, "H", 17);
    System.out.println();
}

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = null;
        printTree(head);

        String pre = preOrderSerialize(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = preOrderUnSerialize(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = unserialByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = preOrderSerialize(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = preOrderUnSerialize(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = unserialByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = preOrderSerialize(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = preOrderUnSerialize(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = unserialByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = preOrderSerialize(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = preOrderUnSerialize(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = unserialByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

    }
}

