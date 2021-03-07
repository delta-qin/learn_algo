package com.deltaqin.code05_graph;

import com.deltaqin.code05_graph.structure.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author deltaqin
 * @date 2021/3/6 7:20 下午
 */

// 图的宽度优先遍历
// 利用队列实现
// 从源节点开始依次按照宽度进入队列，然后弹出
// 每弹出一个点，把该节点所有没有进过队列的邻接点(Set 实现）放入队列
// 直到队列变空

public class G01_BFS {

//    宽度优先
    public static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();

        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
//            将当前节点得到的邻接节点那些没有加入过set的加入到set，同时加入到queue，保证了唯一性
            for (Node item : poll.nexts) {
                if (!set.contains(item)) {
//                    注意加入set的时机，是加入队列的时候而不是从队列弹出的时候，
//                    因为要保证队列里面没有重复的元素是在加上的时候就保证
//                    而不是弹出的时候
                    set.add(item);
                    queue.offer(item);
                }
            }
        }
    }
}
