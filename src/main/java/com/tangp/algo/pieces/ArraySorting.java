package com.tangp.algo.pieces;

import java.util.Arrays;
import java.util.Random;

public class ArraySorting {

    public static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        if (tail - head <= 1) {
            if (arr[head] > arr[tail]) {
                swap(arr, head, tail);
            }
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                swap(arr, i, j);
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomIntArray();
//        System.out.println(Arrays.toString(arr));
//        qSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

        arr = generateRandomIntArray();
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static int[] generateRandomIntArray() {
        Random random = new Random();
        int[] arr = new int[10 + random.nextInt(30)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public static void mergeSort(int[] arr) {
        mergeSortInner(arr, 0, arr.length);
    }


    /**
     * 归并排序，其实就是将两个子数组合并为一个新的数组并返回。
     * 需要额外的空间O(n)；
     *
     * @param arr
     * @param start
     * @param end
     */
    private static void mergeSortInner(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        int i = start, j = mid + 1, k = 0;
        mergeSortInner(arr, i, mid);
        mergeSortInner(arr, j, end);
        int[] temp = new int[end - start + 1];

        while (k < temp.length) {
            if (i <= mid && (j >= end || arr[i] <= arr[j])) {
                temp[k++] = arr[i];
                i++;
            } else if (j < end) {
                temp[k++] = arr[j];
                j++;
            }
        }
        for (k = 0; k < temp.length; k++) {
            arr[start + k] = temp[k];
        }
    }
}
