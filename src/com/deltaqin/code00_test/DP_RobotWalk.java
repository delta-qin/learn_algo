package com.deltaqin.code00_test;

/**
 * @author deltaqin
 * @date 2021/3/10 9:32 上午
 */

// 假设有排成一行的 N 个位置，记为 1~N，N 一定大于或等于 2。
// 开始时机器人在其中的 M 位 置上(M 一定是 1~N 中的一个)，机器人可以往左走或者往右走，
// 如果机器人来到 1 位置， 那 么下一步只能往右来到 2 位置;
// 如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置。
// 规定机器人必须走 K 步，最终能来到 P 位置(P 也一定是 1~N 中的一个)的方法有多少种。
// 给 定四个参数 N、M、K、P，返回方法数。
public class DP_RobotWalk {

    public static int ways(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        // 总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
        return walk(N, M, K, P);
    }

    // N : 位置为1 ~ N，固定参数
    // cur : 当前在cur位置，可变参数
    // rest : 还剩res步没有走，可变参数
    // P : 最终目标位置是P，固定参数
    // 该函数的含义：只能在1~N这些位置上移动，当前在cur位置，走完rest步之后，停在P位置的方法数作为返回值返回
    public static int walk(int N, int cur, int rest, int P) {
        // 如果没有剩余步数了，当前的cur位置就是最后的位置
        // 如果最后的位置停在P上，那么之前做的移动是有效的
        // 如果最后的位置没在P上，那么之前做的移动是无效的
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        // 如果还有rest步要走，而当前的cur位置在1位置上，那么当前这步只能从1走向2
        // 后续的过程就是，来到2位置上，还剩rest-1步要走
        if (cur == 1) {
            return walk(N, 2, rest - 1, P);
        }
        // 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
        // 后续的过程就是，来到N-1位置上，还剩rest-1步要走
        if (cur == N) {
            return walk(N, N - 1, rest - 1, P);
        }
        // 如果还有rest步要走，而当前的cur位置在中间位置上，那么当前这步可以走向左，也可以走向右
        // 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
        // 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
        // 走向左、走向右是截然不同的方法，所以总方法数要都算上
        return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
    }

    public static int ways1(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }

        // 缓存二维表，[还剩rest步][当前在cur位置]的种数，还剩rest步，当前在cur位置的方法总数
        int[][] dp = new int[K + 1][N + 1];
        for(int i=0; i<=K; i++) {
            for(int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }

        // 总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
        return walk(N, M, K, P);
    }

    // 记忆化搜索版本（啥也没干就是加了一个缓存）还是递归，没有纠结位置依赖
    // 如何定义缓存含义，就是看谁是递归函数的可变参数，对应的值就是递归函数的返回值
    public static int walk1(int N, int cur, int rest, int P , int[][] dp) {
        if(dp[rest][cur] != -1) {
            return dp[rest][cur];
        }

        // 缓存没有命中
        if (rest == 0) {
            // 记录缓存
            dp[rest][cur] = cur == P ? 1 : 0;
        }
        if (cur == 1) {
            dp[rest][cur] = walk(N, 2, rest - 1, P);
        }
        if (cur == N) {
            dp[rest][cur] = walk(N, N - 1, rest - 1, P);
        }
        dp[rest][cur] = walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
        return dp[rest][cur] ;
    }

    // 严格表结构的动态规划：记忆化搜索 + 位置依赖 版本
    // 最后要的就是 dp中走了K步到达P位置的值
    // 开始关心具体的位置是怎么依赖之前的值的
    public static int ways2(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        // 缓存二维表，还剩rest步][当前在cur位置]的种数，还剩rest步，当前在cur位置的方法总数
        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                // 下面填写dp 的过程肯定就是从小往大，对应的就是递归从大到小再到大中的后半部分
                // 当前剩余步数K=0，当前位置在N的时候走法
                // 当前剩余步数K=1，当前位置在N的时候走法（位置依赖顺序：依赖于左上和右上）
                // 当前剩余步数K=2，当前位置在N的时候走法（位置依赖顺序：依赖于左上和右上）
                // ... ...
                if (j == 1) {
                    // 二维矩阵中如果当前在1位置，只能到达2位置，而且剩余步数-1，
                    // 最左列依赖的值就是右上角的值（根据递归依赖上一级），只有那一种走法
                    dp[i][j] = dp[i - 1][2];
                } else if (j == N) {
                    // 二维矩阵中如果当前在N位置，只能到达N-1位置，而且剩余步数-1，
                    // 最右列依赖的值就是左上角的值，只有那一种走法
                    dp[i][j] = dp[i - 1][N - 1];
                } else {
                    // 如果当前是中间位置。依赖的就是左上角的和右上角的值做累加
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }
    // 注意上面的步骤，动态转移方程就是我在尝试的东西





    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
        System.out.println(ways(7, 4, 9, 5));
    }
}
