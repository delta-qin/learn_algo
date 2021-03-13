package com.deltaqin.code01_sort;

import java.util.Arrays;

/**
 * @author deltaqin
 * @date 2021/3/13 4:28 下午
 */
public class S05_HeapSort {


    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // 使用元素建立大堆，堆只需要保证arr的第一个元素是最大的就可以，其余不管
        for (int i=0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int size = arr.length;
        // 第一个是最大值，所以和堆的最后一个交换，并且将当前元素移除出堆里面
        swap(arr, 0, --size);
        // 当size为0 的时候堆的元素全部拿出来，从小到达排好了
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            //left + 1 < size 保证不越界
            //  arr[left + 1] > arr[left] 左右子节点哪个大
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            // 左右子节点和当前比谁大
            largest = arr[largest] > arr[index] ? largest : index;

            // 如果最大的就是自己
            if (index == largest) break;

            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void heapInsert(int[] arr, int index) {
        // 完全二叉树子节点找自己的父节点有规律
        // 只要比自己的父节点小，一直往上交换，记得交换之后更新节点当前所在位置
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index -1)/2);
            index = (index-1) /2;
        }
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
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }



}
