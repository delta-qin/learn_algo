package com.deltaqin.code07_recursion;

import java.util.Stack;

/**
 * @author deltaqin
 * @date 2021/3/7 10:14 上午
 */
// 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。 如何实现?
// 写递归，在于试法本身
public class R05_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
//        获取栈底的元素
        int i = getAndRemoveLastElement(stack);
//        又一个递归保存信息的
        reverse(stack);
        // 卡在上面了，
//        递归最底层开始返回，得到的栈就反过来了
        stack.push(i);
    }

    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
//            栈不为空的时候一直调用这个函数，压入函数栈，相当于用内存里面的函数调用栈来代替内存中真实的栈
            int last = getAndRemoveLastElement(stack);
            // 注意，卡在上面了，直到执行了上面的if
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }

}

