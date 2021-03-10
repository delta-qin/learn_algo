package com.deltaqin.code08_dp;

/**
 * @author deltaqin
 * @date 2021/3/10 7:41 下午
 */

// 请同学们自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，
// 棋盘的最左下 角是(0,0)位置。那么整个棋盘就是横坐标上9条线、纵坐标上10条线的一个区域。
// 给你三个 参数，x，y，k，返回如果“马”从(0,0)位置出发，必须走k步，
// 最后落在(x,y)上的方法数 有多少种？
public class D03_HorseJump {

    public static int getWays(int x, int y, int step) {
        return process(x, y, step);
    }

    /**
     * 从00出发，到达xy，规定必须走step步，所有的方法数
     *  如果只剩下一步就可以到达xy，step-1 的位置是xy周围的八个点的位置
     *
     *  base Case注意边界条件
     *      递归调用的时候越界直接返回0，也就是当前方法不成立直接为0
     * @param x
     * @param y
     * @param step
     * @return
     */
    private static int process(int x, int y, int step) {

        if (x < 0 || y < 0 || x > 8 || y > 9) {
            return 0;
        }

        // 步数为0，如果要到达的位置就是(0,0)，返回一种走法，如果要达到的不是(0,0)，返回没有走法0种
        if (step == 0) {
            return (x == 0 && y == 0) ? 1 : 0;
        }

        // 根据象棋的规则可以知道来到当前位置可以是周围的八个点，如果这个8个点有的地方是到不了的就会返回 0
        return process(x-1, y-2, step-1) + process(x+1, y-2, step-1) +
                process(x-2, y-1, step-1) + process(x+2, y-1, step-1) +
                process(x-2, y+1, step-1) + process(x+2, y+1,step-1) +
                process(x-1, y+2, step-1) + process(x+1, y+2, step-1);
    }

    //=======严格表结构版本====================================================
    //=======严格表结构版本====================================================
    public static int ways(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || step < 0) {
            return 0;
        }

        // 画出来三维的是一个长方体，得到的是长方体顶面的某一个x,y 的位置
        // x变化的范围是0-8，y是0-9,s是0-step
        int[][][] dp = new int[9][10][step+1];

        // base case转换为第0层的面只有在起始的位置0,0 是0
        //java中int 数组默认的初始值就是0
        dp[0][0][0] = 1;


        // 根据递归函数的调用参数 ，当前的step层只和上一层有关，依赖关系就明确了，假设知道了第一层，
        // 已经知道了，所以一点点往上累加就可以了，第0层推出第1层，第1层推出第2层。。。
        for (int h = 1; h <= step; h++) {
            // 只要保证最外层的是层数从0到step，里面的两个循环是x还是y都是可以的
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 10; c++) {
                    dp[r][c][h] += getValue(dp, r - 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c + 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c + 1, h - 1);
                }
            }
        }
        return dp[x][y][step];
    }

    public static int getValue(int[][][] dp, int row, int col, int step) {
        // 相当于是整个立方体在0的海洋里
        if (row < 0 || row > 8 || col < 0 || col > 9) {
            return 0;
        }
        return dp[row][col][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(getWays(x, y, step));
        System.out.println(ways(x, y, step));
    }
}
