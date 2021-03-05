package com.deltaqin.code00_test;

public class BsTest {
    public static int indexOfLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = l + (r - l)/2;

            if (nums[mid] < target) {
                l = mid + 1; // 之后的区间是[mid+1, r)，因为是向下取整，且l<r，所以不可能到达r
            } else {
                r = mid;
            }
        }
        return l; // 两个指针已经重合
    }

    public static int indexOfLeft1(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = l + (r - l + 1)/2;

            if (nums[mid] >= target) {
                r = mid-1;
            } else {
                l = mid;
            }
        }
        return l; // 两个指针已经重合
    }
}
