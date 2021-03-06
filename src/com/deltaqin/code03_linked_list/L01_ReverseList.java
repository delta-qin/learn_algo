package com.deltaqin.code03_linked_list;

//单链表
//双向链表
//步骤基本一致，就是多了一步而已
/**
 * @author deltaqin
 * @date 2021/3/5
 */
public class L01_ReverseList {
//    定义链表节点结构
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

//    不用额外的空间。反转链表
    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
//        当前的下一个存起来修改当前的下一个为当前的前一个，之后把当前作为前一个，把当前的下一个作为当前
        while (head != null) {
//            当前的下一个暂存
            next = head.next;
//            当前指向当前的上一个
            head.next = pre;
//            当前变成上一个
            pre = head;
//            暂存的下一个作为当前
            head = next;
        }

        // 不能返回head，退出循环的时候head是null，也就是链表结尾
        return pre;
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int v) {
            value = v;
        }
    }

    public static DoubleNode revers(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null) {
//            自己的下一个暂存起来
            next = head.next;
//            自己指向自己的前一个
            head.next = pre;
//            就多加了这一句
            head.last = next;
//            更新pre变量为自己
            pre = head;
//            把head修改为自己的下一个
            head = next;
        }

        return pre;
    }

    public static void printLinkedList(Node  head) {

        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printLinkedList1(DoubleNode  head) {

        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        printLinkedList(head1);
        head1 = reverseList(head1);
        printLinkedList(head1);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;
        printLinkedList1(head2);
        printLinkedList1(revers(head2));
    }
}
