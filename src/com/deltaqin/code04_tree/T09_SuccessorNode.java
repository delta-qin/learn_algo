package com.deltaqin.code04_tree;

/**
 * @author deltaqin
 * @date 2021/3/6 3:36 下午
 */
// 在二叉树中找到一个节点的后继节点
// 只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。
// 其实就是中序遍历，但是不让遍历整个树而只给定要找的节点

// 前继节点：中序遍历的前一个
// 后继节点：中序遍历的下一个
    // 有右树：右树上的最左节点
    // 无右树：往上走，看节点是不是父亲的左孩子，是那父亲就是该节点的后继
        // 最后的节点后继就是空

public class T09_SuccessorNode {

    // 该结构比普通二叉树节点结构多了一个指向父节点的parent指针。
    // 假设有一棵Node类型的节点组成的二叉树，树中每个节点的parent指针都正确地指向自己的父节点，
    // 头节 点的parent指向null。
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null){
            // 当前节点有右树，后继就是右树上的最左节点
            return getLeftMost(node.right);
        }
        else {
            Node parent = node.parent;
//            当前节点是父亲的右孩子就继续往上回溯
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
//            直到当前节点是父亲的左孩子或者父亲是null走到了结尾
            return parent;
        }
    }

//    返回当前节点的最左子节点
    private static Node getLeftMost(Node right) {
        if (right == null) return null;

        Node left = right;
        while (left.left != null) {
            left = left.left;
        }
        return left;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
