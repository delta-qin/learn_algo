package com.deltaqin.code07_recursion;

/**
 * @author deltaqin
 * @date 2021/3/7 11:00 上午
 */

// N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列， 也不在同一条斜线上。
// 给定一个整数n，返回n皇后的摆法有多少种。 n=1，返回1。
// n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。 n=8，返回92。

//    每一行去试，天然可以做到每一行只有一个，接下来只需要保证新进来的不同行不同斜线就可以了
public class R08_NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // 第 i 行的皇后放在哪一列
        int[] record = new int[n];
//        0 ~ n-1去试，n行是一个终止位置
        return process1(0, record, n);
    }

    // 来到第 i 行
    // record[0...i-1]表示之前的 i 行，放了皇后的位置列
    // n 表示棋盘有多少行
    // 返回值是摆好所有的皇后有多少摆法
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            // 终止行。来到了这里说明之前的试法成立，找到了一种合法的
            return 1;
        }
        // 每一行的皇后摆法要清零
        int res = 0;
        for (int j = 0; j < n; j++) {
            // 验证当前record放在i行j列是否有效，不共列共斜线
            if (isValid(record, i, j)) {
//                可以在这一列点上 i 行的皇后
                record[i] = j;
                // 深度优先，也就是继续试后续（从上往下）
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    // 返回 i 行皇后，放到了 j 列，是否有效
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) { // 只需判断之前的 i 行
            // 不可能在同一行
            // 在同一列 || 与之前的某一行的 坐标横纵距离相等，就false
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


    // =====================常数优化，位运算==========================
    // int 做的位运算，只处理1-32皇后
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 8 皇后，后面8个1，前面全是 0
        // 限制递归结束条件，固定的不变
        int upperLim = n == 32 ? -1 : (1 << n) - 1;
        return process2(upperLim, 0, 0, 0);
    }

    // colLim 列限制
    // leftDiaLim 左斜线限制
    // rightDiaLim 右斜线限制
    // 上述三个初始值都是 n 个0
//
    // 如果某一行的某一列放了，那么列限制上的这一列就不能放1了，因为已经放了。
    // 三个限制 或运算 就是总限制，所以在某一行上，只可以选为0 的位置，为1的位置是上面限制的不能选的，
    // 虽然只规定下一行，但是这个限制是之前所有的列产生的
    public static int process2(int upperLim, int colLim, int leftDiaLim,
                               int rightDiaLim) {
        // 每一行都放完就找到一种有效的
        if (colLim == upperLim) {
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        // 三个限制或就是总限制，
        // 所以在某一行上，只可以选为0 的位置，为1的位置是上面限制的不能选的，
        // pos就是现在可以能在那些位置点皇后，为1的位置
        pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos != 0) {
            // 提取出状态中最右侧的1
            mostRightOne = pos & (~pos + 1);
            // pos 有几个1，while就尝试几次
            pos = pos - mostRightOne;

            // colLim | mostRightOne, 列限制的更新，在那个位置放了就在哪个位置放1就行
            // (leftDiaLim | mostRightOne) << 1,左限制的更新，或之后要左移，因为是斜线，相对于上一行就是要左移
            // (rightDiaLim | mostRightOne) >>> 1);右限制的更新，或之后要右移，因为是斜线，相对于上一行就是要右移
//            下一行怎么试的逻辑，返回有多少有效的
            res += process2(upperLim, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
