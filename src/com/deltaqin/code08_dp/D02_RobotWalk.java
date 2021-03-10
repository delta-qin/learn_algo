package com.deltaqin.code08_dp;

/**
 * @author deltaqin
 * @date 2021/3/10 8:21 上午
 */
// 假设有排成一行的 N 个位置，记为 1~N，N 一定大于或等于 2。
// 开始时机器人在其中的 M 位 置上(M 一定是 1~N 中的一个)，机器人可以往左走或者往右走，
// 如果机器人来到 1 位置， 那 么下一步只能往右来到 2 位置;
// 如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置。
// 规定机器人必须走 K 步，最终能来到 P 位置(P 也一定是 1~N 中的一个)的方法有多少种。
// 给 定四个参数 N、M、K、P，返回方法数。
public class D02_RobotWalk {

    //=======递归版本====================================================
    //=======递归版本====================================================
    public static int ways1(int N, int M, int K , int P) {
        if (N < 2 || M < 1 || K < 1 || M > N || P > N || P < 1)
            return 0;
        // M 位置，还剩下 K 步，时候到达 P 的方法数
        return process(N, M, K, P);
    }

    // 递归函数意义：cur 位置，还剩下rest步，时候到达aim的方法数
    // N : 位置为1 ~ N，固定参数
    // cur : 当前在cur位置，可变参数
    // rest : 还剩res步没有走，可变参数
    // aim : 最终目标位置是P，固定参数
    private static int process(int n, int cur, int rest, int aim) {
        //base case
        if (rest == 0) {
            if (cur == aim) return 1;
            else return 0;
        }

        // 三种情况，
        // 最左侧只能往右走
        if (cur == 1) {
            return process(n, cur+1, rest-1, aim);
        }

        //最右侧只能往左走
        if (cur == n) {
            return process(n, cur-1, rest-1, aim);
        }

        //中间位置可以往两边走
        return process(n, cur-1, rest-1, aim) + process(n, cur+1, rest-1, aim);
    }



    //=======记忆化搜索版本====================================================
    //=======记忆化搜索版本====================================================
    public static int ways2(int N, int M ,int K, int P) {
        if(N <2 || M <1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        // 当前位置：1~N,0的时候是无效的-1
        // 剩余步数：0~K
        int[][] dp = new int[N+1][K+1];
        for (int i = 0; i < N+1; i++){
            for (int j = 0; j < K+1; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(N, M, K, P, dp);
    }

    private static int process1(int n, int cur, int rest, int aim, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }

        //base case
        if (rest == 0) {
            if (cur == aim)
                dp[cur][rest] = 1;
            else dp[cur][rest] = 0;
        }

        // 三种情况，
        // 最左侧只能往右走
        if (cur == 1) {
            dp[cur][rest] = process(n, cur+1, rest-1, aim);
        }

        //最右侧只能往左走
        if (cur == n) {
            dp[cur][rest] = process(n, cur-1, rest-1, aim);
        } else {
            //中间位置可以往两边走
            dp[cur][rest] = process(n, cur-1, rest-1, aim) + process(n, cur+1, rest-1, aim);
        }
        return dp[cur][rest];
    }


    //=======严格表结构版本====================================================
    //=======严格表结构版本====================================================
    public static int ways3(int N, int M, int K, int P) {
        if (N <2 || M < 1 || M >N || K <1 || P <1 ||P>N) {
            return 0;
        }

        // 当前位置：1~N,0的时候是无效的-1
        // 剩余步数：0~K
        int[][] dp = new int[N+1][K+1];
        //忘记注释下面的，，，出了问题
        //for (int i = 0; i < N+1; i++){
        //    for (int j = 0; j < K+1; j++) {
        //        dp[i][j] = -1;
        //    }
        //}

        //base case
        for (int cur = 0; cur < N+1; cur++) {
            if (cur == P) {
                dp[cur][0] = 1;
            }
            else dp[cur][0] = 0;
        }

        // [cur][rest] 肯定是一列列的，base case只告诉了第一列。而且在我们的dp矩阵中，下一列是依赖上一列的
        // 没设计好，如果xy互换一下，就是下一行依赖上一行了，这里就先按照列来处理
        //按照列
        for (int rest = 1; rest < K+1; rest++) {
            //再按照行
            for (int cur= 1; cur < N+1; cur++) {
                // 三种情况，
                // 最左侧只能往右走,依赖左下角
                if (cur == 1) {
                    dp[cur][rest] = dp[cur+1][rest-1];
                }

                //最右侧只能往左走，依赖左上角
                if (cur == N) {
                    dp[cur][rest] = dp[cur-1][rest-1];
                } else {
                    //中间位置可以往两边走，依赖左下角和左上角
                    dp[cur][rest] = dp[cur+1][rest-1] + dp[cur-1][rest-1];
                }
            }
        }
        // 确定最终结果
        return dp[M][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
        System.out.println(ways3(7, 4, 9, 5));
    }
}
