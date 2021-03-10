package com.deltaqin.code08_dp;

/**
 * @author deltaqin
 * @date 2021/3/9 5:18 下午
 */
public class D01_MinCoins {


    //=======递归版本====================================================
    //=======递归版本====================================================
    public static int minCoins1(int[] arr, int aim) {

        // 当前位置为0 开始在arr 找要求的aim，返回最少的硬币数
        return process(arr, 0, aim);
    }

    /**
     * arr[index...] 组成出 rest ，返回  需要的最少硬币数
     * @param arr 货币，固定参数
     * @param index 当前来到的位置，可变参数
     * @param rest 剩余需要凑的钱， 可变参数
     * @return
     */
    public static int process(int[] arr, int index, int rest) {

        if (rest < 0) {
            // 剩余的钱小于0，说明前面不对，无法组成
            return -1;
        }

        if (rest == 0) {
            //成功，当前不需要硬币
            return 0;
        }

        // 当前剩余需要凑的钱是 > 0 的，但是arr里面的硬币都尝试了，也就是无法组成，返回-1 失败
        if (index == arr.length) {
            return -1;
        }

        // 当前需要凑的钱的是 > 0 的，而且arr 里面的硬币还没有尝试完
        // 用剩下的硬币去获取
        // 不使用当前
        int noUse = process(arr, index + 1, rest);
        // 使用当前的
        int use = process(arr, index + 1, rest - arr[index]);

        if(noUse == -1 && use == -1) {
            // 用不用都凑不起来。返回-1
            return -1;
        } else if (noUse == -1) {
            // 只有用当前硬币行得通，返回use加上当前硬币，use就是使用的硬币个数
            return use +1;
        } else if (use == -1){
            // 不用行得通，返回不用的个数
            return noUse;
        } else {
            // 用不用都可以，返回最小的
            return Math.min(use+1 , noUse);
        }
    }

    //=======记忆化搜索版本====================================================
    //=======记忆化搜索版本====================================================
    public static int minCoins2(int[] arr, int aim) {

        //可变参数是当前index 以及剩余要凑的钱数，对应的值的当前使用的最少硬币
        // index的取值范围是 0 - arr.length, 剩余的钱数是0-aim
        // 所以定义的时候要+1，就是对应的数组长度，
        int[][] dp = new int[arr.length + 1][aim+1];

        //初始化表
        //-1 用来标记无效解，0 是具体的答案，用-2表示这个表里面的数据有没有算过
        for(int i = 0 ; i <= arr.length; i++) {
            for(int j = 0; j <= aim; j++) {
                dp[i][j] = -2;
            }
        }
        // 当前位置为0 开始在arr 找要求的aim，返回最少的硬币数
        return process2(arr, 0, aim, dp);
    }

    /**
     * arr[index...] 组成出 rest ，返回  需要的最少硬币数
     * @param arr 货币，固定参数
     * @param index 当前来到的位置，可变参数
     * @param rest 剩余需要凑的钱， 可变参数
     * @return
     */
    public static int process2(int[] arr, int index, int rest,  int[][] dp) {
        // 也相当于是中了缓存 ， 只不过是无效的缓存
        if (rest < 0) {
            // 剩余的钱小于0，说明前面不对，无法组成
            return -1;
        }

        if (dp[index][rest] != -2) {
            return dp[index][rest];
        }

        if (rest == 0) {
            //成功，当前不需要硬币
            // 返回之前记录在dp里面
            dp[index][rest] = 0;
            //return 0;
        }else if (index == arr.length) {
            // 当前剩余需要凑的钱是 > 0 的，但是arr里面的硬币都尝试了，也就是无法组成，返回-1 失败
            dp[index][rest] = -1;
            //return -1;
        } else {

            // 当前需要凑的钱的是 > 0 的，而且arr 里面的硬币还没有尝试完
            // 用剩下的硬币去获取
            // 不使用当前
            int noUse = process(arr, index + 1, rest);
            // 使用当前的
            int use = process(arr, index + 1, rest - arr[index]);

            if(noUse == -1 && use == -1) {
                // 用不用都凑不起来。返回-1
                dp[index][rest] = -1;
                //return -1;
            } else if (noUse == -1) {
                // 只有用当前硬币行得通，返回use加上当前硬币，use就是使用的硬币个数
                dp[index][rest] = use +1;
                //return use +1;
            } else if (use == -1){
                // 不用行得通，返回不用的个数
                dp[index][rest] = noUse;
                //return noUse;
            } else {
                // 用不用都可以，返回最小的
                dp[index][rest] = Math.min(use+1 , noUse);
            }
        }
        return dp[index][rest];
    }


    //=======严格表结构版本====================================================
    //=======严格表结构版本====================================================

    /**
     *
     * 明确终止要的状态 0，aim， index表示的是当前可以尝试的硬币，aim表示还是剩下的需要凑的rest
     *  base ：如果左侧越界了一律无效解，左侧默认就是-1，也就是rest<0的时候
     *          rest==0 的时候一定会返回0，所以dp的所有反之都是0
     *          rest ！= 0 ，index来到终止位置，就是-1。dp的所有位置都是-1
     *
     *          这些base的情况要在初始的时候给dp设置好，因为后面递增的时候需要使用到base来计算
     *
     *  任何一个格子计算的时候都需要其下方的值（不使用硬币的时候）。以及其下方减去当前位置下方的值（使用硬币的时候）
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins3(int[] arr, int aim) {

        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];

        //这些是递归的时候才需要的，动态规划的时候运动的方式就是for循环的方式是我们控制的，我们只需要保证后续获取数据的时候自己需要的
        //已经在里面就好了，所以不需要下面的未计算标记
        //for(int i = 0 ; i <= arr.length; i++) {
        //    for(int j = 0; j <= aim; j++) {
        //        dp[i][j] = -2;
        //    }
        //}

        //使用base case初始化一些值
        for (int row = 0; row <= N; row++) {
            // rest 为 0 的时候，已经成功，不需要当前再加硬币，全部是0
            dp[row][0] = 0;
        }
        for (int col = 1; col <= aim; col++) {
            // N 是所有的index都用完了，也就是除了0列是0以外，其余的是用完了index还是没有让rest变为0，就失败了返回-1
            dp[N][col] = -1;
        }

        //剩下的就是中间的位置，
        // row 取最大值就是index最大-1.index最大上面已经给了初始值了。
        for(int index = N-1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                // 不存在
                // 也相当于是中了缓存 ， 只不过是无效的缓存
                //if (rest < 0) {
                //    // 剩余的钱小于0，说明前面不对，无法组成
                //    return -1;
                //}

                //if (dp[index][rest] != -2) {
                //    return dp[index][rest];
                //}

                //if (rest == 0) {
                //    //成功，当前不需要硬币
                //    // 返回之前记录在dp里面
                //    dp[index][rest] = 0;
                //    //return 0;
                //}else if (index == arr.length) {
                //    // 当前剩余需要凑的钱是 > 0 的，但是arr里面的硬币都尝试了，也就是无法组成，返回-1 失败
                //    dp[index][rest] = -1;
                //    //return -1;
                //} else {

                    // 当前需要凑的钱的是 > 0 的，而且arr 里面的硬币还没有尝试完
                    // 用剩下的硬币去获取
                    // 不使用当前
                    //int noUse = process(arr, index + 1, rest);
                int noUse = dp[index+1][rest]; // 当前位置的正下方
                    // 使用当前的
                    //int use = process(arr, index + 1, rest - arr[index]);
                int use = -1;
                /// 开始用新的时候要保证 减去之后 还是正常的，不用的话可以用前面来保证所以不用判断
                if (rest - arr[index] >= 0) {
                    use = dp[index+1][rest-arr[index]];
                }
                    //计算决定了要答案的时候前面都是算过的
                    if(noUse == -1 && use == -1) {
                        // 用不用都凑不起来。返回-1
                        dp[index][rest] = -1;
                        //return -1;
                    } else if (noUse == -1) {
                        // 只有用当前硬币行得通，返回use加上当前硬币，use就是使用的硬币个数
                        dp[index][rest] = use +1;
                        //return use +1;
                    } else if (use == -1){
                        // 不用行得通，返回不用的个数
                        dp[index][rest] = noUse;
                        //return noUse;
                     } else {
                        // 用不用都可以，返回最小的
                        dp[index][rest] = Math.min(use+1 , noUse);
                    }
                //}
            }
        }
        return dp[0][aim];


        //    填写dp的时候是每一个格子的下方以及他的左下角某一个位置（就是将去当前面值对应的rest）
        //    for的顺序是从左往右（0- rest），从下往上（0 - index）。

    }



    // for test
    public static int[] generateRandomArray(int len, int max) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, max);
            int aim = (int) (Math.random() * 3 * max) + max;
            if (minCoins1(arr, aim) != minCoins2(arr, aim) && minCoins1(arr, aim) != minCoins3(arr, aim)) {
                System.out.println("ooops!");
                break;
            }
        }
        System.out.println("success");
    }
}
