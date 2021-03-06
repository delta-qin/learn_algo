package com.deltaqin.code04_tree;

/**
 * @author deltaqin
 * @date 2021/3/6 4:50 下午
 */

// 打印所有的折痕的方向，
//      每折叠一次就相当于多了一层
// 从上往下看折痕其实 就是一个二叉树的中序遍历
// 节点的左侧是凹，右侧是凸，三个折痕的时候上是凹下是凸，
public class T11_PaperFolding {
//    满二叉树，折叠几次就有几层

    public static void print(int time) {
//        当前折是第几次，一共折几次，是左孩子还是右孩子
        process(1, time, true);
    }

    /*// i 是节点的层数。N是一共的层数，down=true 凹 down=false 凸*/
    private static void process(int i,  int time, boolean b) {
        if (i > time) return;

        process(i + 1, time, true );
        System.out.print(b ? "凹  " : "凸  ");
        process(i + 1, time, false);
    }

    public static void main(String[] args) {
        print(2);
    }

}
