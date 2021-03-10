package com.deltaqin.code08_dp;

/**
 * @author deltaqin
 * @date 2021/3/10 8:43 下午
 */
// 给定一个整型数组 arr，代表数值不同的纸牌排成一条线。
// 玩家 A 和玩家 B 依次拿走每张纸 牌， 规定玩家 A 先拿，玩家 B 后拿，
// 但是每个玩家每次只能拿走最左或最右的纸牌，玩家 A 和 玩 家 B 都绝顶聪明。

// 请返回最后获胜者的分数。
public class D04_CardsInLine {

//    递归版本======================
    public static int win1(int[] arr) {
        return Math.max(f(arr, 0, arr.length-1), e(arr, 0, arr.length-1));
    }

    // 先手函数
    // 当前是自己可以先拿，arr[i...j]
    // 返回自己的最好的分数
    public static int f(int[] arr, int l, int r) {
        // base case只有一个时候直接拿走
        if (l == r) {
            return arr[l];
        }

        // 注意自己需要拿，所以调用后手函数的时候需要加上自己选择的金额
        return Math.max(arr[l] + e(arr, l+1, r),
                arr[r] + e(arr,l, r-1));
    }

    // 后手函数
    // 当前自己不可以拿，是对方在arr[i...j]上拿
    // 返回自己的最好的分数
    public static int e(int[] arr, int l ,int r) {
        if (l == r) {
            return 0;
        }
        // 对方先手的时候，拿了i位置，自己就是f(arr, i + 1, j),
        // 对方拿了j位置，自己就是f(arr, i, j - 1)
        // 不管是哪一个，肯定是肯定是想让我得到最小的
        // 对手决定了我在对手拿完i或者j得到了什么

        // 注意自己不需要拿，所以调用先手函数的时候不需要加额外的金额
        return Math.min(f(arr, l+1, r), f(arr, l, r-1));
    }


    //    严格依赖版本======================
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 先手的 ij
        int[][] f = new int[arr.length][arr.length];
        // 后手的 ij
        int[][] s = new int[arr.length][arr.length];
        // 如果是一个有效的范围，i 一定不会大于 j， 因为i是j的左边界
        // 范围上尝试都带有一个特征，下半区是无效区域，因为左边界不能超过右边界
        for (int j = 0; j < arr.length; j++) {
            // 当前只有一张的情况，直接先手拿走
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                // 当前位置求的时候根据递归函数依赖的是另一个矩阵中的左和下的值
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                // 两个交替填好对方的
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        // 确定最终想要的值，就是上面递归版本调用函数的时候的参数。
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);

    }




    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }
}
