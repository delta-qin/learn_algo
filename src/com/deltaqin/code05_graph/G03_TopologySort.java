package com.deltaqin.code05_graph;

import com.deltaqin.code05_graph.structure.Graph;
import com.deltaqin.code05_graph.structure.Node;

import java.util.*;

/**
 * @author deltaqin
 * @date 2021/3/6 7:22 下午
 */

// 拓扑排序（有向无环图）
// 解决循环依赖的问题，一定是有向无环图
// 先看哪个是入度为0的点
// 去除该点及其影响，之后找下一个入度为0的点

public class G03_TopologySort {
    public static List<Node> topologySort(Graph graph) {
//        维护当前节点以及其入度
        HashMap<Node, Integer> hashMap = new HashMap<>();
//        用于存放当前入度为0 的队列
        Queue<Node> queue = new LinkedList<>();

//        构造节点以及对应的入度，入度为0直接加入队列
        for (Node node : graph.nodes.values()) {
            hashMap.put(node, node.in);
            if (node.in == 0) {
                queue.offer(node);
            }
        }

//        拓扑排序的结果，依次放入result
        List<Node> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
//            入度为0的队列依次出来的就是拓扑排好的直接加入结果集
            res.add(cur);
            for (Node node : cur.nexts) {
//                邻接节点的入度都要减1
                hashMap.put(node, hashMap.get(node)-1);
//                入度为0时候就加入到队列
                if (hashMap.get(node) == 0) {
                    queue.add(node);
                }
            }
        }
        return res;

    }
}
