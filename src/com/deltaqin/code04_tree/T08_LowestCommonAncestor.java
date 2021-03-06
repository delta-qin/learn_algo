package com.deltaqin.code04_tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author deltaqin
 * @date 2021/3/6 1:09 下午
 */
// 给定两个二叉树的节点node1和node2，找到他们的最低公共祖先节点
public class T08_LowestCommonAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //    =========方法一：短小，难============================================================
//                      明确递归结束条件，往下找是为了知道什么时候结束递归回溯。也就是遇见null或者遇见要找的节点时候。
//                      两个不是对方的祖先的时候，返回左右子都不为空的节点就是最小祖先。
//                      一个是另一个的祖先的时候，返回其中不为null的节点即可。
//    =====================================
    public static Node lowestCommon1(Node root, Node o1, Node o2) {
//        递归结束的条件
//          要么自己是null也就是到了底部叶子节点的下一个，那应该返回null，就表示上一层递归调用的子节点是null
//          要么是要找的o1或者o2(就没有必要继续往下遍历了，是为了找公共祖先，往下又找不到）
        if (root == null || root == o1 || root == o2) {
            return root;
        }

        Node left = lowestCommon1(root.left, o1, o2);
        Node right = lowestCommon1(root.right, o1, o2);

//        上面的后序遍历结束有两种情况：两个点不互为祖先（返回二者都不是null的祖先），两个点一个是另一个的祖先（返回不为null的即可）

//        1. 当两个点不互相为公共祖先的时候会来这里
//          左子树和右子树都不为null，说明当前root就是他们的公共祖先
        if (left != null && right != null) {
            return root;
        }

//        2. 一个为null 一个 不为null
//          情况一：一个可以是另一个的祖先，向上返回一直到最后又到达这里返回这个祖先
//          情况二：一个可以就是o1或者o2，另外一个在别的分支里，向上返回最后会到达上面的1. if
        return left != null ? left : right;
    }


//    ===========================================================================================
    //    方法二：简单，空间占用大（使用map保存父子关系，使用set实现查找）
//      将每个节点自己的父节点放到map里面，对于o1而言，每次取出节点的父节点放到一个set里面直到到达根节点停止。
//      开始从o2往上找，查看set中是否有，第一次出现的时候就是二者的最小父节点，始终没有出现就是直接返回o1
    public static Node lowestCommon2(Node root, Node o1, Node o2) {

//        递归构造每一个节点以及其父节点对应的map
        HashMap<Node, Node> hashMap = new HashMap<>();
//        根节点的父亲就是自己，用于后面判断是否到达根节点
        hashMap.put(root, root);
//        调用一个递归函数将节点和父节点的关系放入map
        process(hashMap, root);

//        错误，下面的会让o1无法加入set，但是o1可能就是二者的祖先。
//        下面判断o2和set的关系的时候一样的不可以跳过o2直接使用他的父亲
//        Node father = hashMap.get(o1);
        Node father = o1;
        HashSet<Node> set = new HashSet<>();
        while (father != hashMap.get(father)) {
//            两个语句的顺序一开始写反了。
            set.add(father);
            father = hashMap.get(father);// 啊这里居然写了hashMap.get(o1);一直死循环，都没发现
        }

//        注意o2自己也可能是father
        father = o2;
        while (father != hashMap.get(father)) {
            if (set.contains(father)) {
                return father;
            }
            father  = hashMap.get(father);
        }
        return father;
    }

    private static void process(HashMap<Node, Node> hashMap, Node root) {
        if (root == null) return;

        hashMap.put(root.left, root);
        hashMap.put(root.right, root);
        process(hashMap, root.left);
        process(hashMap, root.right);
    }

//    测试用：=============打印树=======================================
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
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.left = new Node(8);
        printTree(head);
        System.out.println("===============");

        Node o1 = head.left.right;
        Node o2 = head.right.left;

        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + lowestCommon1(head, o1, o2).value);
        System.out.println("ancestor : " + lowestCommon2(head, o1, o2).value);
        System.out.println("===============");

    }
}
