package com.deltaqin.code05_graph.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author deltaqin
 * @date 2021/3/6 7:19 下午
 */
public class Graph {
    // 点集
    public HashMap<Integer,Node> nodes;
    // 边集
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
