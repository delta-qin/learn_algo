package com.deltaqin.code04_tree;

import java.util.Stack;

/**
 * @author deltaqin
 * @date 2021/3/5
 */
public class T01_PreInPostOrder {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

//    =======递归实现============================
    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }


//    非递归实现

//    使用一个栈就可以实现先序遍历，先压右再压入左即可
    public static void preOrderUnRecursion(Node root) {
//        不要把null压入栈
        if(root == null) return;


        Stack<Node> stack = new Stack<>();
        stack.push(root);
//        不断有节点弹出，不断有右节点和左节点压入，注意栈和队列的区别，决定是深度优先还是宽度优先
//        先压右后压左保证了弹出时候是先左后右，但是弹出左的时候不是立马弹出右，如果左有孩子先让孩子入栈
//        就实现了深度优先遍历
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.value + " ");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

//    较难理解
    public static void inOrderUnRecursion(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
//        栈不为空，或者（如果树是一个只有右子树的链表形式，流程就是压入一个弹出一个，栈空和root不为空交替出现）
        while (!stack.isEmpty() || root!=null) {
//          如果右子节点是null就继续弹出，如果不是null就开始压入他的左边界
            if (root != null ) {
//                左边界压栈
                stack.push(root);
                root = root.left;
            } else  {
                root = stack.pop();
                System.out.print(root.value + " ");
//                必须更新root，如果是null，则会继续向上，弹出，否则向下开始压入右子节点
                root = root.right;
            }
        }
    }

//    使用两个栈实现
    public static void postOrderUnRecursion(Node root) {
        if (root == null) return;

//        从1栈出来压入2栈，最后从2栈出来就是后序遍历
//        所以压入1栈的时候，是中左右，这样二栈压入的时候就是中右左，弹出的时候就是左右中
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node pop = stack1.pop();
            stack2.push(pop);
            if (pop.left != null) {
                stack1.push(pop.left);
            }
            if (pop.right != null) {
                stack1.push(pop.right);
            }
        }

//        直接弹出2栈中的所有，是左右中的顺序，就是后序遍历
        while (!stack2.isEmpty()) {
            Node pop = stack2.pop();
            System.out.print(pop.value + " ");
        }
    }

//    使用一个栈实现非递归后序遍历（不好理解！！！）
//    还是相当于左边界压栈，但是使用一个栈的时候要判断自己压入的节点是不是之前已经压入并且弹出的，避免重复压入
    public static void postOrderUnRecursion1(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node stackTop = null;
        while (!stack.isEmpty()) {
            stackTop = stack.peek();
            if ( stackTop.left != null && root != stackTop.left && root != stackTop.right) {
//                如果栈顶元素有左子节点，且上一次弹出的不是他的左孩子或者右孩子，那么他就可以入栈，
//                否则说明应该是他的右孩子入栈或者他自己出栈了
                stack.push(stackTop.left);
            } else if (stackTop.right != null && root != stackTop.right) {
//                如果右孩子不为空而且上一次弹出的不是右孩子，那么就可以不弹出栈顶而是把右孩子压入
//                因为是后序遍历，左右都出去，中才出去
                stack.push(stackTop.right);
            } else {
//                来到这里，说明
//                      没有左右孩子
//                      左右孩子都已经加入弹出了
                System.out.println(stack.pop() + " ");
//                记录一下已经加入过栈并且弹出的最近的元素
                root = stackTop;
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.left.left.left = new Node(1);
        root.right.left = new Node(7);
        root.right.left.left = new Node(6);
        root.right.right = new Node(10);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(11);

        System.out.println("\n递归先序遍历");
        preOrder(root);
        System.out.println("\n非递归先序遍历");
        preOrderUnRecursion(root);

        System.out.println("\n递归中遍历");
        inOrder(root);
        System.out.println("\n非递归中遍历");
        inOrderUnRecursion(root);


        System.out.println("\n递归后序遍历");
        postOrder(root);
        System.out.println("\n非递归后序遍历");
        postOrderUnRecursion(root);
    }

}
