package com.deltaqin.code06_greedy_algo;

import java.util.Arrays;

/**
 * @author deltaqin
 * @date 2021/5/16 5:21 下午
 *
 * 硬币最值问题
 *      妙：贪心+回溯
 *      一开始最大的硬币除法，也就是能用就用，每次都是能用多少用多少，不能再依次减去知道为0，说明有他的所有方案都不可用
 *      开始找次优的（因为最优的不可能凑出来）知道最后所有的可能都尝试完了，就没了
 */
public class MinCoin {
    public static void main(String[] args) {
        // 硬币面值
        int[] values = {5, 3};
        // 总价
        int total = 11;
        int minCoinCount = getMinCoinCountOfValueHelper(total, values);

        // 输出结果
        System.out.println(minCoinCount);
    }

    /**
     * @param total 金额
     * @param coins 币种数组，从大到小排序
     * @return 返回币数，如果返回-1表示无法凑够total
     */
    private static int getMinCoinCountOfValueHelper(int total, int[] coins) {
        // coins 相当于是回溯过程中可以用的硬币，如果这个变成了0，说明所有的情况都枚举了
        // 从一开始只拿最大的--到最大的变成0开始次大的递减，可用coins一直在减少
        if (coins.length == 0) {
            return -1;
        }

        //当前币值
        int currentCoin = coins[0];

        //使用当前币值数量
        int useCurrentCoinCount = total / currentCoin;

        int restTotal = total - useCurrentCoinCount * currentCoin;
        // 如果restTotal为0，表示余额已除尽，组合完成
        if (restTotal == 0) {
            return useCurrentCoinCount;
        }

        // 其他币种数量
        int coninCount = -1;
        // 剩余的币种
        int[] restCoins = Arrays.copyOfRange(coins, 1, coins.length);

        // =0，表示当前所有的可能方案都失效，但是还可以找不包含自己的方案，一旦不包含的（递归）（一个大的子问题，自己只是要个结果）也没有找到，那就只能--后退出循环返回-1
        while (useCurrentCoinCount >= 0) {
            // 否则尝试用剩余面值求当前余额的硬币总数
            coninCount = getMinCoinCountOfValueHelper(restTotal, restCoins);

            // 如果后续没有有可用组合,退一步，当前useCurrentCoinCount币数减1
            if (coninCount == -1) {
                // 否则尝试把当前面值数-1
                useCurrentCoinCount--;
                // 重新计算restTotal
                restTotal = total - useCurrentCoinCount * currentCoin;

            } else {
                return useCurrentCoinCount + coninCount;
            }
        }

        return -1;
    }
}
