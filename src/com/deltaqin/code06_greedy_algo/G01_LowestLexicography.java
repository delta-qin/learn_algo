package com.deltaqin.code06_greedy_algo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author deltaqin
 * @date 2021/3/6 9:16 下午
 */

//给定一个字符串类型的数组strs，找到一种拼接方式，使得把所有字符串拼起来之后形成的 字符串具有最小的字典序。

//    贪心策略在实现时，经常使用到的技巧：
//
//1，根据某标准建立一个比较器来排序
// 2，根据某标准建立一个比较器来组成堆
public class G01_LowestLexicography {

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2){
            return (s1+s2).compareTo(s2+s1);
        }
    }

    public static String lowestLexicography(String[] strings) {

        if (strings == null || strings.length == 0) return "";

        MyComparator myComparator = new MyComparator();
//        贪心体现在每两个拼接起来是最小字典序，
        Arrays.sort(strings, myComparator);
        String res = "";
        for (int i=0; i < strings.length; i++) {
            res += strings[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestLexicography(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestLexicography(strs2));

    }
}
