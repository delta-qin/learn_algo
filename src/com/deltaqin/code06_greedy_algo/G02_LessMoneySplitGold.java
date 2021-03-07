package com.deltaqin.code06_greedy_algo;

import java.util.PriorityQueue;

/**
 * @author deltaqin
 * @date 2021/3/6 9:29 下午
 */

// 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金 条，不管切成长度多大的两半，都要花费20个铜板。

// 一群人想整分整块金条，怎么分最省铜板? 例如,给定数组{10,20,30}，代表一共三个人，
// 整块金条长度为10+20+30=60。 金条要分成10,20,30三个部分。
// 如果先把长度60的金条分成10和50，花费60； 再把长度50的金条分成20和30，花费50；一共花费110铜板。
// 但是如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20， 花费30；一共花费90铜板。
// 输入一个数组，返回分割的最小代价。

//    上面直观可以得出，切割的时候越接近中间，所花费的钱就越少
//    但是解题不是这个思路
//    反向加的思想，起步时候就使用最小的金额。一直使用小堆顶累加得到最后的结果、

//    也就是切割的逆向，就是拼接
public class G02_LessMoneySplitGold {
    public static int lessMoney(int[] arr) {
        // 准备一个小根堆，所有数字放到小根堆里
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }

        //
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            // 每次堆里弹出两个数字结合之后放回小根堆
            cur = pQ.poll() + pQ.poll();
            // 累加和
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

    public static void main(String[] args) {
        // solution
        int[] arr = { 6, 7, 8, 9 };
        System.out.println(lessMoney(arr));

    }

}
