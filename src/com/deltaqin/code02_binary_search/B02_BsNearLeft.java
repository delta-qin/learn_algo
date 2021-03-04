package com.deltaqin.code02_binary_search;

//在一个有序数组中，找>=某个数最左侧的位置
public class B02_BsNearLeft {
//    如果num值大于等于mid的值，mid值存起来，直到最后左右指针相遇，存下的值就是要返回的，注意必须相遇，否则找不到最值
    public static  int nearLeft(int[] sortedArr, int num) {
        int l = 0;
        int r = sortedArr.length -1;
        int index = -1;

        while (l < r) {
            int mid = l + ((r-l) >> 1);
            if (sortedArr[mid] >= num) {
                index = mid;
                r = mid -1;
            } else {
                l = mid +1;
            }
        }

        return index;
    }
}
