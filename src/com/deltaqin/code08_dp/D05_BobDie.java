package com.deltaqin.code08_dp;

/**
 * @author deltaqin
 * @date 2021/3/10 9:05 下午
 */

// 给定五个参数n,m,i,j,k。
// 表示在一个N*M的区域，Bob处在(i,j)点，每次Bob等概率的向上、 下、左、右四个方向移动一步，
// Bob必须走K步。如果走完之后，Bob还停留在这个区域上， 就算Bob存活，否则就算Bob死亡。
// 请求解Bob的生存概率，返回字符串表示分数的方式。
public class D05_BobDie {

    // NM 是限制区域
    //ij 是当前所在的位置
    // K 是要求运动的步数
    //返回概率
    public static String bob1(int N, int M, int i, int j,int K) {
        long all = (long)Math.pow(4, K);

        long live = process(N, M, i, j, K);

        long gcd = gcd(all, live);

        return String.valueOf((live/gcd) + "/" + (all/gcd));
    }

    /**
     * 当前位置走 k 步不越界的方法数
     */
    // 固定参数：int N, int M
    // 可变参数：int row, int col, int rest
    public static long process(int N, int M, int row, int col, int rest) {
        if (row < 0 || row == N || col < 0 || col == M) {
            // 越界了死去
            return 0;
        }
        // 没有越界而且走完了，
        if (rest == 0) {
            return 1;
        }
        // 没走完也没有越界
        // 往上往下往左往右都是
        // 		每一次高层只需要低层的东西
        return process(N, M, row - 1, col, rest - 1) +
                process(N, M, row + 1, col, rest - 1) +
                process(N, M, row, col - 1, rest - 1) +
                process(N, M, row, col + 1, rest - 1);
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static String bob2(int N, int M, int i, int j,int K) {
        int[][][] dp = new int[N + 2][M + 2][K + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                dp[row][col][0] = 1;
            }
        }

        for (int rest = 1; rest <= K; rest++) {
            for (int row = 1; row <= N; row++) {
                for (int col = 1; col <= M; col++) {
                    dp[row][col][rest] = dp[row - 1][col][rest - 1];
                    dp[row][col][rest] += dp[row + 1][col][rest - 1];
                    dp[row][col][rest] += dp[row][col - 1][rest - 1];
                    dp[row][col][rest] += dp[row][col + 1][rest - 1];
                }
            }
        }
        long all = (long) Math.pow(4, K);
        long live = dp[i + 1][j + 1][K];
        long gcd = gcd(all, live);
        return String.valueOf((live / gcd) + "/" + (all / gcd));
    }


    public static void main(String[] args) {
        int N = 10;
        int M = 10;
        int i = 3;
        int j = 2;
        int K = 5;
        System.out.println(bob1(N, M, i, j, K));
        System.out.println(bob2(N, M, i, j, K));
    }
}
