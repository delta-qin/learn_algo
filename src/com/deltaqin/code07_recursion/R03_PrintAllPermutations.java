package com.deltaqin.code07_recursion;

import java.util.ArrayList;

/**
 * @author deltaqin
 * @date 2021/3/7 9:55 上午
 */
// 打印字符串全部的排列
// 打印字符串全部的排列，要求不出现重复的排列

public class R03_PrintAllPermutations {
    public static ArrayList<String> getAllPermutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs, 0, res);
        // res.sort(null);
        return res;
    }

//    从左往右试的办法
    // 在 i 到最后的所有字符，都在 i 的位置尝试
    // 0- i-1是之前做的选择
    // 所有的全排列加到res里面去

    // 打印全排列的代码
    public static void process(char[] chs, int i, ArrayList<String> res) {
        if (i == chs.length) {
            res.add(String.valueOf(chs));
        }
        // 用于杀死不可能的分支，某个字符有没有尝试过
        boolean[] visit = new boolean[26];
        // i 往后所有的字符都可以来到 i 位置
        for (int j = i; j < chs.length; j++) {
            // 去重全排列，也可以得到之后把重复的去掉
            if (!visit[chs[j] - 'a']) {
                // 尝试过的就不再排列
                visit[chs[j] - 'a'] = true;
                // 一个决定，某一个字符放到了 i 位置，交换过去
                swap(chs, i, j);
                // 处理下一个字符
                process(chs, i + 1, res);
                // 又交换回来了
                swap(chs, i, j);
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(getAllPermutation("abc"));
    }
}

