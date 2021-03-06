package com.deltaqin.code01_sort;

import org.omg.CORBA.ARG_IN;

import java.util.Arrays;

/**
 * @author deltaqin
 * @date 2021/3/4
 */
public class S03_InsertionSort {

    private static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

//        左边先排好
        for (int i = 1; i < arr.length-1 ; i++ ){
            int tmp = arr[i];
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j , j-1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int index){
//        arr[i] = arr[i] ^ arr[index];
//        arr[index] = arr[i] ^ arr[index];
//        arr[i] = arr[i] ^ arr[index];

        int tm = arr[i];
        arr[i] = arr[index];
        arr[index] = tm;
    }

    //    =================结束=======================
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
//        随机数是0-1，乘起来取整就是0-maxSize的随机数
        int[] arr = new int[ (int)((maxSize+1) * Math.random())];
        for(int i = 0; i < arr.length; i++) {
//            (int)((maxValue+1) * Math.random()) 可以产生 0- MaxValue的随机数
//            相减可以得到负数
            arr[i] = (int)((maxValue+1) * Math.random()) - (int)((maxValue) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if(arr == null) {
            return null;
        }

        int[] tmp = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            tmp[i] = arr[i];
        }
        return tmp;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if((arr1 == null && arr2 != null) ||
                (arr1 != null && arr2 == null)){
            return false;
        }

        if(arr1 == null && arr2 == null) {
            return true;
        }

        if(arr1.length != arr2.length) {
            return false;
        }

        for(int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr){
        if(arr == null) {
            return;
        }

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed  =true;

        for(int i = 0; i < testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            insertionSort(arr1);
            comparator(arr2);
            boolean equal = isEqual(arr1, arr2);
            if (!equal) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "success" : "fail");



    }


}

