package com.deltaqin.code07_recursion;

/**
 * @author deltaqin
 * @date 2021/3/7 10:49 上午
 */

// 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值。
// 给定一个正数bag，表示一个载重bag的袋子，你装的物 品不能超过这个重量。返回你能装下最多的价值是多少？
public class R07_Knapsack {

    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process1(weights, values, 0, 0, bag);
    }

//    还是从左往右尝试，暴力递归
    // 从 i 号往后的货物自由选择，形成的最大价值返回，不超过bag
    // 之前的决定达到的重量是 alreadyweight
//    返回的是最大的value
    public static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
//        超重了这个方案就不应该有
        if (alreadyweight > bag) {
            return 0;
        }
//        没超重，但是结束了，所以当前 i 往后的价值是0，不管前面的
        if (i == weights.length) {
            return 0;
        }
        return Math.max(
                // 不要 i 号货物，不会获得 i 号货物的价值，交给 i + 1号货物决定
                process1(weights, values, i + 1, alreadyweight, bag),
                // 要 i 号货物 values[i] ，做对自己最有利的选择
//                alreadyweight + weights[i] 加上当前的重量作为 i+1 看到的already
                values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag));
    }


//    可变参数变多了，固定的不管，越简单越容易改动态规划
    public static int process2(int[] weights, int[] values, int i, int alreadyweight, int alreadyValue, int bag) {
//        超重了这个方案就不应该有
        if (alreadyweight > bag) {
            return 0;
        }
//        没超重，但是结束了，所以当前 i 往后的价值是0，不管前面的
        if (i == weights.length) {
            return alreadyValue;
        }
        return Math.max(
                // 不要 i 号货物，不会获得 i 号货物的价值，交给 i + 1号货物决定
                process2(weights, values, i + 1, alreadyweight, alreadyValue, bag),
                // 要 i 号货物 values[i] ，做对自己最有利的选择
//                alreadyweight + weights[i] 加上当前的重量作为 i+1 看到的already
                values[i] + process2(weights, values, i + 1,
                        alreadyweight + weights[i],
                        alreadyValue + values[i] ,bag));
    }


//    动态规划
    public static int maxValue3(int[] c, int[] p, int bag) {
        int[][] dp = new int[c.length + 1][bag + 1];
        for (int i = c.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + c[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(maxValue3(weights, values, bag));
        System.out.println(maxValue3(weights, values, bag));
    }

}

