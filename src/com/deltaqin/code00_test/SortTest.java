package com.deltaqin.code00_test;

import java.util.Arrays;

/**
 * @author deltaqin
 * @date 2021/3/4
 */
public class SortTest {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

//        易错
        for (int i = 0; i < arr.length - 1; i++) {
//            易错
            int minIndex = i;
//            易错
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i , minIndex);

        }
    }

    public static void swap(int[] arr, int l , int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
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
            selectionSort(arr1);
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
