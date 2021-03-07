package com.deltaqin.code05_graph.structure;

import java.util.ArrayList;

/**
 * @author deltaqin
 * @date 2021/3/6 7:19 下午
 */
public class Node {
    public int value;
    // 入度：别人到自己的个数
    public int in;
    // 出度
    public int out;
    // 直接邻居，当前点出发，由其发散出去的边连的点有哪些
    public ArrayList<Node> nexts;
    // 属于自己的边
    public ArrayList<Edge> edges;

    public Node(int v ) {
        value = v;
        in = 0;
        out = 0;
        edges = new ArrayList<>();
        nexts = new ArrayList<>();
    }
}
