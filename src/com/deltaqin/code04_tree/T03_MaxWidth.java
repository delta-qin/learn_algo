package com.deltaqin.code04_tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//求一棵二叉树的宽度
/**
 * @author deltaqin
 * @date 2021/3/6
 */
public class T03_MaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

//    使用一个map记录节点所在的层，当遍历新的层的时候记录当前的最大宽度
//    宽度优先遍历还是使用队列来做
    public static int maxWidth(Node root) {
        if (root == null) return 0;

        HashMap<Node, Integer> hashMap = new HashMap<>();
        hashMap.put(root, 1);
        int max = 1;
//        注意Poll是LinkedList自己的方法，不要使用多态，ArrayList也没有
//        List<Node> list = new LinkedList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;
        int width = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
//            如果有孩子就加入到队列里面，并且将孩子和它的层数放入map里面
            if (poll.left != null) {
                queue.add(poll.left);
                hashMap.put(poll.left, hashMap.get(poll)+1);
            }
            if (poll.right != null){
                queue.add(poll.right);
                hashMap.put(poll.right, hashMap.get(poll)+1);
            }

//            如果弹出的节点的层数和当前所在层数不相等，说明换行了，要更新并且记录最大值，否则只需要增加宽度即可
            if (level != hashMap.get(poll)) {

                width = 1;
                level++;
            } else {
                width++;
            }
            max = Math.max(width, max);
        }
        return max;
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
        System.out.println(maxWidth(root));

    }
}
