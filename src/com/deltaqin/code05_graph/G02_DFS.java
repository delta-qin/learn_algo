package com.deltaqin.code05_graph;

import com.deltaqin.code05_graph.structure.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author deltaqin
 * @date 2021/3/6 7:20 下午
 */
// 图的深度优先遍历（待定）
// 使用栈实现
// 从源节点开始把节点按照深度压入栈，然后弹出
// 每弹出一个节点，把节点下一个没有进过栈的邻接点放入栈
// 直到栈变空
public class G02_DFS {

    public static void dfs(Node node) {
        if (node == null) return;
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        set.add(node);
        stack.push(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(next)) {

                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
