package com.deltaqin.code03_linked_list;

//给定两个有序链表的头指针head1和head2，打印两个链表的公共部分。
//快慢指针
public class L02_PrintCommonPart {
    public static class Node{
        public int value;
        public Node next;

        public Node(int c) {
            value = c;
        }
    }

    public static void printCommonPart(Node head1, Node head2) {
//        肯定是要定义两个相同的指针
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.print(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static  void print(Node head){
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String [ ]args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        print(node1);
        print(node2);
        printCommonPart(node1, node2);
    }
}
