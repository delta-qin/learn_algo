package com.deltaqin.code01_sort;

import java.util.Arrays;

/**
 * @author deltaqin
 * @date 2021/3/13 4:29 下午
 */
public class S06_QuickSort {

    private static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            // 将分区的pivot放在数组的最后
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            // 对数组分区，将分好的两个数组，前面的最后一个index和后面的最前一个index放在数组里返回
            int[] p = partition(arr, l, r);
            //p[0] 是左分区的右边界，闭区间
            quickSort(arr, l, p[0]);
            // p[1] 是右分区的左边界，闭区间
            quickSort(arr, p[1], r);
        }
    }


    //分区
    // r位置的元素是pivot
    // 初始左边界是-1（left），右边界是r（right）,（闭区间）
    // 小于pivot的时候，和左分区外的第一个元素++left交换，游标右移动
    // 大于pivot的时候，和右分区外的第一个元素--right交换，游标不移动
    // ==pivot的时候，游标直接移动，不扩展分区的边界
    private static int[] partition(int[] arr, int l, int r) {
        int i = l;
        // 注意初始化左右分区的边界的时候由于是闭区间，所以一开始是最左和最右之外的一个
        int less = l - 1;
        int right = r;
        //less 往右，right往左，当游标和right相遇的时候，就分好了
        while (i < right) {
            if (arr[i] < arr[r]) {
                // 当前值小于pivot，交换了左分区外的元素和游标的位置
                // 因为++less可能是==pivot的值，这样就保证==pivot的值不会放在左分区里面
                swap(arr, ++less, i++);
            } else if (arr[i] > arr[r]) {
                // 当前值大于pivot，交换了右分区外的一个和当前，当前游标不动
                swap(arr, i, --right);
            } else {
                // 当前游标的值==pivot，直接往右移动，不扩展左分区的边界
                i++;
            }
        }
        // 交换当前pivot和游标
        swap(arr, i, r);
        return new int[]{less, right};
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr);
        printArray(arr);
    }


}
