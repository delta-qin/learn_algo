package com.deltaqin.code03_linked_list;

import java.util.HashMap;

//注意更新链表的是后尾节点的指针也要重新指定，不能指定完next就跑路了
// 将一个有随机指针的链表复制出来
/**
 * @author deltaqin
 * @date 2021/3/5
 */
public class L05_CopyListWithRandomPointer {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node (int v){
            value = v;
        }
    }

//    借助map来实现
    public static Node copy(Node head) {
//        map的k是当前链表的节点，map的v是待连接链表的节点
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node node = map.get(cur);
            node.next = map.get(cur.next);
            node.rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }


//    在链表每一个节点后面新增一个节点，之后再借助每一个前面连起来，之后将二者分开
    public static Node copy1(Node head) {

        if (head == null) {
            return null;
        }

//        在链表每一个节点后面插入一个相同值的链表
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

//        处理rand指针,注意有的rand可以是null
        cur = head;
        while (cur != null) {
            cur.next.rand = (cur.rand == null ? null : cur.rand.next);
            cur = cur.next.next;
        }

//        分离两个链表
        cur = head;
        Node tmp = null;
        Node tmp1 = head.next;
        while (cur !=null) {
            next = cur.next.next;
            if (tmp == null) {
//                头节点
                tmp = cur.next;
            } else {
                tmp.next = cur.next;
                // 更新当前的tmp
                tmp = tmp.next;
            }
//            保证原来也是对的
            cur.next = next;
            cur = next;
        }
        return tmp1;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
//        printRandLinkedList(head);
//        res1 = copy(head);
//        printRandLinkedList(res1);
//        res2 = copy1(head);
//        printRandLinkedList(res2);
//        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        System.out.println("=======方法一==================");
        printRandLinkedList(head);
        res1 = copy(head);
        printRandLinkedList(res1);
        printRandLinkedList(head);
        System.out.println("========方法二=================");
        res2 = copy1(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
