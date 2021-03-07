package com.deltaqin.code05_graph.structure;

/**
 * @author deltaqin
 * @date 2021/3/6 7:19 下午
 */
// 最后是将给定的数据结构转换为定义好的熟悉的图的结构
public class GraphConverter {
//    假设给定一个二维数组，每一行三列，分别是weight权重，from节点上的值，to节点上的值
    public static Graph creatGraph(Integer[][] matrix) {
        Graph graph = new Graph();
//        读取每一行数据转换到自己熟悉的图中
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge edge = new Edge(weight,fromNode, toNode);
            fromNode.edges.add(edge);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            graph.edges.add(edge);
        }
        return graph;
    }
}
