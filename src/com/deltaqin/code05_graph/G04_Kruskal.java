package com.deltaqin.code05_graph;

import com.deltaqin.code05_graph.structure.Edge;
import com.deltaqin.code05_graph.structure.Graph;
import com.deltaqin.code05_graph.structure.Node;

import java.util.*;

/**
 * @author deltaqin
 * @date 2021/3/6 7:22 下午
 */

// Kruskal，最小生成树算法：连通，且边的权值和是最小的
// 要求无向图
// 从最小的边开始考虑，如果加上之后有环，就不加
public class G04_Kruskal {
    public static class MySets{
        public HashMap<Node, List<Node>> setMap;

        public MySets(List<Node> nodes) {
            for (Node node: nodes) {
                List<Node> list = new ArrayList<>();
                list.add(node);
                setMap.put(node, list);
            }
        }

    }

    public static class EdgeComparator1 implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            // 小边到大边
            return o1.weight - o2.weight;
        }

    }

//    public static Set<Edge> kruskalMST(Graph graph) {
//        MySets mySets = new MySets((List<Node>) graph.nodes.values());
//        // 边按照从小到大的顺序实现
//        // priorityQueue 堆按照边的权值大小依次排序
//        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator1());
//        for (Edge edge : graph.edges) {
//            // n条边就是logn
//            priorityQueue.add(edge);
//        }
//
//
//    }
}
