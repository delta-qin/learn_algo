package com.deltaqin.code04_tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author deltaqin
 * @date 2021/3/6 8:38 上午
 */
// 中序遍历，得到升序就是BST
public class T04_IsBST {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node (int v){
            value = v;
        }
    }

//    方法一：先把中序遍历写出来，判断当前值的时候判断自己是否小于自己的前一个值即可（判断的时候有点绕）
    public static int pre = Integer.MIN_VALUE;
    public static boolean isBst1(Node root) {
//        递归结束条件，来到叶子节点的时候获取其左右子节点的时候发生
        if (root == null) return true;

        boolean bst1 = isBst1(root.left);
        if (!bst1) {
            return false;
        }

//        遍历是按照左中右来的，所以保证当前节点大于自己前一个节点即可
        if (root.value <= pre) {
            return false;
        } else {
//            更新前一个值为当前值
            pre = root.value;
        }

//        这里需要返回右子树是不是BST，递归策略
        return isBst1(root.right);
    }

//    方法二：第一种没有使用额外的空间，可以将中序遍历的结果放入队列，判断队列中的元素是不是升序
//      这种更加直观，将中序遍历和判断升序分离
    public static boolean isBst2(Node root) {
        if (root == null) return true;

        LinkedList<Node> linkedList = new LinkedList<>();
        inOrder(root,linkedList);

//        错误版本。一开始就空指针异常了
//        Node pre = null;
//        for (Node item : linkedList) {
//            if (pre.value >= item.value) {
//                return false;
//            }
//            pre = item;
//        }

        int pre = Integer.MIN_VALUE;
        for (Node item : linkedList) {
            if (pre >= item.value) {
                return false;
            }
            pre = item.value;
        }
        return true;
    }

    public static void inOrder(Node root, LinkedList<Node> list) {
        if (root == null) return;

        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }

//    方法三：和方法一一样，只是使用非递归的方式实现了中序遍历
    public static boolean isBst3(Node root) {
        if (root == null) return true;

        int pre = Integer.MIN_VALUE;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
//        把root != null条件忘记了
        while (!stack.isEmpty() || root != null) {
//            不在这里弹出，这里先全部压入栈里面。直到没有左孩子的时候才开始弹出
//            if (root != null) { 不是 if (root.left != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                Node pop = stack.pop();

                if (pre >= pop.value) {
                    return false;
                }
                pre = pop.value;

                root = pop.right;
            }
        }
        return true;
    }

//方法四：使用后序遍历：保证左右子树都是BST，并且中大于左子树的最大值，小于右子树的最小值
//    定义递归函数返回的数据结构，需要包含当前子树是否是BST，以及当前子树的最小值和最大值
    public static class ReturnBean{
        public boolean isBst;
        public int min;
        public int max;

        public ReturnBean(boolean i, int mi, int ma) {
            isBst = i;
            min = mi;
            max = ma;
        }

    @Override
    public String toString() {
        return "ReturnBean{" +
                "isBst=" + isBst +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}

    public static ReturnBean isBst4(Node root) {

        if (root == null) return null;

        ReturnBean left = isBst4(root.left);
        ReturnBean right = isBst4(root.right);

        int min = root.value;
        int max = root.value;

//        当前树的最大值和最小值是由中间值以及左右子树的最值决定的，如上两个if所示
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }

        boolean isBst = true;

//        当前的保证左子树和右子树都是BST，且中间大于左子树的最大值，小于右子树的最小值
        if (left != null && (!left.isBst || left.max >= root.value)) {
            isBst = false;
        }
        if (right != null && (!right.isBst || right.min <= root.value)) {
            isBst = false;
        }

        return new ReturnBean(isBst, min, max);
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
        System.out.println(isBst1(root));
        System.out.println(isBst2(root));
        System.out.println(isBst3(root));
        System.out.println(isBst4(root));

    }
}
