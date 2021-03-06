package com.deltaqin.code03_linked_list;

// 将单向链表按某值pivot划分成左边小、中间相等、右边大的形式
//可以复制到数组交换完了再复制回去
//也可以直接新建三个链表最后合并，注意建立三个链表的时候Node的next要清空
/**
 * @author deltaqin
 * @date 2021/3/5
 */
public class L04_SmallerEqualBigger {

    public static class Node{
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

//    ==============方法一：使用数组实现划分之后转回链表 。空间O(n)=========================================
    public static Node partition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }

        Node cur = head;
//        统计链表节点的个数,用来新建一个对应长度的数组
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }

//        申请和链表长度一致的数组将其复制过去
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }

        arrPartition(nodeArr, pivot);

//        将数组的节点按照顺序连接起来，返回链表
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i-1].next = nodeArr[i];
        }
        nodeArr[i-1].next = null;
        return nodeArr[0];
    }

    private static void arrPartition(Node[] nodeArr, int pivot) {
//        设置左右指针，初始值都是越界的,因为l和r分别是左右子区间，无法保证左右边界一定属于左右区间
        int l = -1;
        int r = nodeArr.length;
//        从左往右遍历的指针
        int index = 0;

        while (index != r) {
            if (nodeArr[index].value < pivot) {
//                初始值为-1  的 l 此时才到了0，有了第一个元素
                swap(nodeArr, ++l , index++);
            } else if (nodeArr[index].value == pivot) {
//                巧妙，注意上面换的时候先让 l自增再交换，扩充了左子集且顺序没乱
                index++;
            } else {
//                思想和处理左子集是一样的
                swap(nodeArr, --r, index);
            }
        }
    }

    public static void swap(Node[] arr, int i, int j) {
        Node tmp = arr[i];
        arr[i]  = arr[j];
        arr[j] = tmp;
    }

//    ============================================================================
//    ============================================================================
//    方法二：可以使用三个链表分别维护左中右，之后将三个链表连起来
//    为了表示三个链表的开始和结束，而且不修改node的next值，所以需要3对表示头尾的node
    public static Node partition2(Node head, int pivot) {

        Node hl = null;
        Node hr = null;
        Node ml = null;
        Node mr = null;
        Node tl = null;
        Node tr = null;

        Node cur = head;
        Node next = null;
        while (cur != null) {
//            将下一个节点的暂存起来
            next = cur.next;
//            当前节点拼接上去的时候要null自己连接的谁，因为是切分到了三个链表上了
            cur.next = null;
            if (cur.value < pivot) {
                if (hl == null) {
                    hl = cur;
                    hr = cur;
                } else {
//                    相当于链表新增节点
//                    先将节点连上去，再将指针指向最后一个节点
                    hr.next = cur;
                    hr = cur;
                }
            } else if (cur.value == pivot) {
                if (ml == null) {
                    ml = cur;
                    mr = cur;
                } else {
                    mr.next = cur;
                    mr = cur;
                }
            } else  {
                if (tl == null) {
                    tl = cur;
                    tr = cur;
                } else {
                    tr.next = cur;
                    tr = cur;
                }
            }
            cur = next;
        }

        // small and equal reconnect
        if (hr != null) {
            hr.next = ml;
            mr = mr == null ? hr : mr;
        }
        // 中间连右边，这里mr只要左和中有一个存在，就不会是null，因为上面执行已经保证了mr一定有值，被替换了！！
        if (mr != null) {
            mr.next = tl;
        }
//        左中右依次 哪一个头不空返回哪一个
        return hl != null ? hl : ml != null ? ml : tl;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = partition2(head1, 5);
        printLinkedList(head1);

    }
}
