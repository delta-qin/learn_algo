package com.deltaqin.code07_recursion;

/**
 * @author deltaqin
 * @date 2021/3/6 11:00 下午
 */
// 局部拆的是对的，整体就一定是对的
//    打印n层汉诺塔从最左边移动到最右边的全部过程
public class R01_Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            process(n, "左", "右", "中");
        }
    }

    public static void process(int i, String from, String to , String other) {
        if (i == 1) {
            System.out.println("移动 " + 1 + " 从 " + from + " 到 " + to);
        } else {
            // i - 1 整体 从from移动到 other
            process(i - 1,  from, other, to);
            // i 从from 移动到 to
            System.out.println("移动 " + i + " 从 " + from + " 到 " + to);
            // i - 1 整体 从other移动到to
            process(i-1, other, to , from);
        }
    }

    public static void main(String[] args) {
        hanoi(5);
    }
}
