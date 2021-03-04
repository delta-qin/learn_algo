package com.deltaqin.code02_binary_search;

//在一个有序数组中，找某个数是否存在
public class B01_BsExist {

    public static boolean bsExist(int[] arr, int num) {
        if (arr == null || arr.length == 0){
            return false;
        }

        int l = 0;
        int r = arr.length -1;
        int mid = 0;

        while (l < r){
            mid = l + ((r-l) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return arr[l] == num;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,5};
        int num = 2;

        boolean e = bsExist(arr, num);
        System.out.println( e ? "ok" : "fail");
    }
}
