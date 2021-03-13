package com.deltaqin.code00_test;

import java.util.Arrays;

/**
 * @author deltaqin
 * @date 2021/3/13 3:16 下午
 */
public class Sort0313 {
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        //// 一直前推直到合适,最后一个也需要，不能-1
        //for (int i = 0; i < arr.length; i++) {
        //    // 往左移动就是--，往右移动就是++
        //    //j = i+1 会导致右侧越界
        //    for (int j = i+1; j > 0; j--) {
        //        if (arr[j] < arr[j-1]) {
        //            swap(arr, j, j-1);
        //        } else {
        //            break;
        //        }
        //    }
        //}

        // 都是从右往左比
        for (int i = 1; i<arr.length; i++) {
            for (int j = i; j > 0; j--) {
                // j 不能取到0， 不能取到最右侧arr.length，严谨数组越界问题
                if (arr[j] < arr[j-1]) {
                    swap(arr,j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void selectionSort(int[] arr1) {
        if (arr1 == null || arr1.length < 2) return;

        for (int i= 0; i < arr1.length-1; i++){
            int minIndex = i;
            for (int j = i; j < arr1.length; j++) {
                if (arr1[j] < arr1[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr1, i, minIndex);
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j+1, j);
                }
            }
        }
    }


//    快排
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        process(arr, 0, arr.length-1);
    }

    private static void process(int[] arr, int l, int r) {
        //if (l == r) return;

        if (l < r) {

            swap(arr, l + (int)(Math.random()*(r-l+1)), r);
            int[] p = partition(arr, l, r);
            process(arr, l, p[0]);
            process(arr,p[1],r);
        }
        // l +
    }

    private static int[] partition(int[] arr, int l, int r) {
        int youbiao = l;
        int left = l-1;
        // 注意 r就是pivot，不是右分区的
        int right = r;

        while (youbiao < right) {
            if (arr[youbiao] < arr[r]) {
                swap(arr, youbiao++, ++left);
            } else if (arr[youbiao] > arr[r]) {
                // 这里写错了，这个游标不要动，因为不知道换过来的是个啥
                swap(arr, youbiao, --right);
            } else {
                youbiao++;
            }
        }

        swap(arr, youbiao, r);
        return new int[]{left, right};
    }

    //    =================================================
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] copyArray(int[] arr) {
        if(arr == null) {
            return null;
        }
        int[] tmp = new int[arr.length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = arr[i];
        }
        return tmp;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new  int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue+1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null){
            return true;
        }

        if ((arr1 == null && arr2 != null) ||
                (arr1 != null && arr2 == null)) {
            return false;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++ ){
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray (int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main (String[] args) {
        int maxValue = 100;
        int maxSize = 100;
        int maxTime = 50000;
        boolean succeed = true;

        for (int i = 0; i < maxTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            insertSort(arr1);
            selectionSort(arr1);
            bubbleSort(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)){
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "ok" : "fail");
    }


}
