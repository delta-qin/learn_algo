package com.deltaqin.code06_greedy_algo;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author deltaqin
 * @date 2021/3/6 9:46 下午
 */
// 输入： 正数数组costs 正数数组profits 正数k 正数m
// 含义： costs[i]表示i号项目的花费
// profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
// k表示你只能串行的最多做k个项目
// m表示你初始的资金
// 说明： 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。 输出： 你最后获得的最大钱数。
public class G04_MaxMoneyDoWork {
    public static class Node {
        // 花费
        public int p;
        // 利润
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {

        // 花费组织的小根堆的比较器
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Node> {

        // 利润组织的大根堆的比较器
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }

    }

    // K 最多可执行项目，W是启动资金
    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Node[] nodes = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new Node(Profits[i], Capital[i]);
        }

        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        // 所有按照花费大小放到小根堆里面
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }

        // 最多执行
        for (int i = 0; i < k; i++) {
            // 所有花费小于W的都加入到用利润排序的大根堆
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            // 还是没有项目可以做，都是自己做不了的，永远做不了了
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            // 周而复始把所有返回
            W += maxProfitQ.poll().p;
        }
        return W;
    }


    // 可以使用暴力递归的方法测试，每一个作为第一个项目，排列组合
}
