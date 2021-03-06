package com.deltaqin.code03_linked_list;

import java.util.Stack;

//判断一个链表是否为回文结构
//
//【题目】给定一个单链表的头节点head，请判断该链表是否为回文结构。
// 【例子】1->2->1，返回true； 1->2->2->1，返回true；15->6->15，返回true； 1->2->3，返回false。
// 【例子】如果链表长度为N，时间复杂度达到O(N)，额外空间复杂度达到O(1)。
//
// 1）额外数据结构记录（哈希表等） 2）快慢指针
/**
 * @author deltaqin
 * @date 2021/3/5
 */
public class L03_Palindrome {

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

//    笔试借助额外空间
//    可以将全部放入栈，也可以先使用双指针找到中间位置，只将后面的放入栈里面，再弹出判断
    public static boolean isPalindrome0(Node head) {
        Stack<Node> stack = new Stack<>();
//        cur是用来保证head不变的，后面用来弹出时候比较移动
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            Node tmp = stack.pop();
            if (head.value != tmp.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


//    面试使用双指针，然后使用链表反转反转右侧半个链表，一起平移判断
    public static boolean isPalindrome1(Node head) {

        if (head == null || head.next == null) {
            return true;
        }
//        先移动到中间
        Node l = head;
        Node r = head;
//        while (l != null && r != null) {
//        上面的条件判断有误，画5个6个节点的链表就知道了，会出现空指针异常，必须保证while里面可以
//        到达的最远距离不为空， && 左右两个也不可以互换
        while (r.next != null && r.next.next != null){
            r = r.next.next;
            l = l.next;
        }
//        将第一个链表的结尾置为空
//        画图可知，奇数时候此时l位于中间节点，偶数时候此时位于前一个链表的结尾
//        设置好第二个链表的起点，将第一个链表的结尾设置为null
        r = l.next;
        l.next = null;

//        反转第二个链表
        Node pre = r;
        Node next = null;
        while ( r != null) {
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }

//        从head和r 一起移动监测
//        画出奇数偶数节点可知其实只需要保证r 不为null就可以了
        while (head != null && r != null) {
            if (head.value != r.value) {
                return false;
            }
            head = head.next;
            r = r.next;
        }
        return true;
    }

    public static void print(Node head) {

        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        print(head);
        System.out.print(isPalindrome0(head) + " | ");
        System.out.print(isPalindrome1(head) + " | ");
        print(head);
        System.out.println("=========================");

    }
}
