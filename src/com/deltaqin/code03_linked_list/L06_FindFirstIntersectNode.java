package com.deltaqin.code03_linked_list;

//两个单链表相交的一系列问题
//
//【题目】给定两个可能有环也可能无环的单链表，头节点head1和head2。
// 请实 现一个函数，如果两个链表相交，请返回相交的 第一个节点。
// 如果不相交，返 回null
//
// 【要求】如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
/**
 * @author deltaqin
 * @date 2021/3/5
 */
public class L06_FindFirstIntersectNode {

//    如果一个有环一个没有环，不可能相交，因为有环就出不去了，走不到相交的位置
//    两个都没环
//        长的先走差值步，之后两个一起走，相等时候就相遇
//    两个都有环
//        先找到环的起始位置，
//          可能在环外相交，这和两个都没环一样。长的走差值步之后一起走
//          可能在环上相交。返回其中一个的入环起点即可


    public static class Node{
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

//    入口
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop2 = getLoopNode(head2);
        Node loop1 = getLoopNode(head1);
//        如果都有环
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
//        如果都没有环
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

//        一个有一个没有，不可能相交
        return null;

    }

    private static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;

        int n = 0;

//        cur1.next  不是cur1，尽早退出是为了得到最后一个，因为是单向链表，但是想要获取最后一个节点是否一样
        while (cur1.next != null ){
            n++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

//        都到了结尾处，如果结尾都不一样，没有必要后续的了
        if (cur1 != cur2) {
            return null;
        }

        // cur1设置为长的，cur2设置为短的
        cur1 = n>0? head1 : head2;
        cur2 = cur1==head1 ? head2 : head1;

        n = Math.abs(n);
        while (n != 0 ){
            n--;
            cur1 = cur1.next;
        }

//        走到这里就是一定会相遇了，所以没有什么附加的判断，循环就完事，最后一定会相等的
        while (cur2 != cur1) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
//
//        环的入口一致
        if (loop1 == loop2) {
            int n = 0;
            Node cur1 = head1;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            Node cur2 = head2;
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }

            cur1 = n < 0 ? head2 : head1;
            cur2 = cur1==head1 ? head2 : head1;

            n = Math.abs(n);
            while (n!=0) {
                n--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        }
//        环的入口不一致，从环入口的下一个开始走，如果再次回到入口没有遇到另外一个入口，二者就不会有交集
//                      遇到的话 直接返回其中一个入口即可

        else {
            Node cur = loop1.next;
            while (cur != loop1) {
                if (cur == loop2) {
                    return loop1;
                }
                cur = cur.next;
            }

            return null;
        }
    }

//    判断一个链表是否有环，有的话返回入口，没有返回null
    private static Node getLoopNode(Node head) {
//       至少三个才可以成环
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

//        快慢指针开始走，有环一定相遇。没环快的会有null
        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
//                快指针到达结尾处。无环
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }

//        已经知道有环了，开始找环的起点
//        二者相遇的时候，一个节点从头开始走，一个节点从当前位置开始走，最后一定相遇
//        而且相遇的位置就是环的入口（数学证明挺绕的）
        n2 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);


    }
}
