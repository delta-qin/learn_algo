package com.deltaqin.code02_binary_search;

//二分查找 [LeetCode852 山脉顶点位置]
// 数组长度>=3，给定的是山脉数组，3个一定是中间，不用管
/**
 * @author deltaqin
 * @date 2021/3/4
 */
public class B04_PeakIndexMountainArray {

    public int peak(int[] arr) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid  = l + ((r-l)>>1);
            if (arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]) {
                return mid;
            }
            if (arr[mid] > arr[mid-1]) {
                l = mid;
            } else if (arr[mid] > arr[mid +1 ]) {
                r = mid;
            }
        }
        return -1;
    }

}
