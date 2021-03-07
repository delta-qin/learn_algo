package com.deltaqin.code07_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deltaqin
 * @date 2021/3/7 8:15 上午
 */

// 全部子序列（不需要连续，但是顺序不能变），包括空
public class R02_PrintAllSubsquences {

    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0);
    }

    // 这种方法利用了递归会保留当前的栈，实现了空间的复用，先置为0，打印返回之后再恢复
    public static void process(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        process(chs, i + 1);
        char tmp = chs[i];
        chs[i] = '-';
        process(chs, i + 1);
        chs[i] = tmp;
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    // ================================================================
    // 看做是一个递归树，走还是不走两条路
    // chs 是对应的字符串
    // i 是当前来到的位置：后面两个递归分别是走和不走形成的
    // res 是已经选择的字符形成的列表
    public static void process(char[] chs, int i, List<Character> res) {
        // 不管走还是不走，i 的值在递归的时候都是+1，不是i++，不要改i，因为不需要连续，但是顺序不能变
        if(i == chs.length) {
            printList(res);
        }

        // 要当前字符的路
        List<Character> resKeep = copyList(res);
        resKeep.add(chs[i]);
        process(chs, i+1, resKeep);

        // 不要当前字符的路
        List<Character> resNoInclude = copyList(res);
        process(chs, i+1, resNoInclude);
    }

    public static void printList(List<Character> res) {
        // ...;
        System.out.println(res);
    }

    public static List<Character> copyList(List<Character> list){
        return null;
    }


    public static void main(String[] args) {
        String test = "abc";
        printAllSubsquence(test);
    }

}

