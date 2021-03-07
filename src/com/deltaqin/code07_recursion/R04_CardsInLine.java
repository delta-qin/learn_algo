package com.deltaqin.code07_recursion;

/**
 * @author deltaqin
 * @date 2021/3/7 10:00 上午
 */

//给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸 牌，
// 规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A 和玩家B都绝顶聪明。
// 请返回最后获胜者的分数。
public class R04_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 玩家A先手：f(arr, 0, arr.length - 1)
        // 玩家B后手 s(arr, 0, arr.length - 1));
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    // 先手函数定义，自己决定拿什么，自己肯定是拿所有利益最大的
//        如果拿了左侧，那么就加上右侧所有对应的最大后手，
//        如果是拿了右侧，那么就加上左侧所有的对应的最大后手
//    给我一段来试（常规试法：从左往右试，或者选一段试，每一个题不一样）
    public static int f(int[] arr, int i, int j) {
//        先手就直接拿走
//        base case定义好
        if (i == j) {
            return arr[i];
        }
//        arr[i] + s(arr, i + 1, j) 当前利益arr[i]是拿走左侧的就获得， s(arr, i + 1, j)  是后手情况为【i + 1, j】的最大分数
//        arr[j] + s(arr, i, j - 1) 当前利益arr[j]是拿走右侧的就获得，s(arr, i, j - 1) 是后手情况为【i, j - 1】的最大分数
//        绝对聪明的人，选二者的最大值
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    // 后手，别人决定了我拿什么，别人肯定是想让我得到最小的
    public static int s(int[] arr, int i, int j) {
//        只有一个那就是被先手拿走了，自己的利益就是0
//        base case定义好
        if (i == j) {
            return 0;
        }
//        先手拿走了 i， 我就从 i+1,j 来先手
//        先手拿走了 j, 我就从 i，j-1 来先手
//        问题是我的决定是对方决定的，对方预判了你的预判，所以后手拿到的一定是最小的，先手拿的一定是对后手最不利的
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }


//    有了试法，变成动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }

}
