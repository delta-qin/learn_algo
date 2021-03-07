package com.deltaqin.code05_graph.structure;

/**
 * @author deltaqin
 * @date 2021/3/6 7:19 下午
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
