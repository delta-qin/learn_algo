package com.deltaqin.code02_binary_search;

// 局部最小值
// 给定一个含有N个不同整数的数组，找到一个局部最小元素，满足a[i]<a[i-1],且a[i]<a[i+1]的索引i。

//当a[i]的前后两个元素都存在时，需要满足“a[i] < a[i-1]，且a[i] < a[i+1]”这个条件，
// 但是如果a[i]是第一个元素或者是最后一个元素，那么只需要看一边。
// 所以对于任何一个数组，”局部最小元素“一定是存在的
/**
 * @author deltaqin
 * @date 2021/3/4
 */
public class B03_BsLocalMin {
    public static int findNum(int[] arr) {
        int len = arr.length;

        if (arr == null || len == 0) {
            return -1;
        }

        if (len == 1 || arr[0] < arr[1]) {
            return 0;
        }

        if (arr[len-2] > arr[len-1]) {
            return len-1;
        }

        int l = 0;
        int r = len -1;
//        while (l < r) {
//            int mid = l + ((r - l) >> 1);
//            if (arr[mid-1] > arr[mid] && arr[mid+1] > arr[mid]) {
//                return mid;
//            } else if (arr[mid-1])
//
//        }
        return -1;
    }
}
