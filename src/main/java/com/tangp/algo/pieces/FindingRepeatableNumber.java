package com.tangp.algo.pieces;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pengtang
 * <p>
 * 从一个有序的数组里找出出现次数大于length / k的数。
 */
public class FindingRepeatableNumber {

    public static int[] find(int[] arr, int k) {
        if (arr == null || k <= 0 || arr.length < k) {
            return new int[]{};
        }

        final int threshold = arr.length / k;

        if (threshold == 1) {
            return arr;
        }


        List<Integer> out = new LinkedList<>();

        int counting = 1, currentNum = arr[0];
        for (int index = 1; index < arr.length; index++) {
            if (counting == 1 && k > arr.length + 1) {
                break;
            }

            if (arr[index] == currentNum) {
                counting++;
            } else {
                if (counting > threshold) {
                    out.add(currentNum);
                }

                counting = 1;
                currentNum = arr[index];
            }
        }

        if (out.size() > 0) {
            final int[] outArr = new int[out.size()];
            int count = 0;
            for (Integer integer : out) {
                outArr[count++] = integer;
            }
            return outArr;
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 0, 2, 2, 2, 2, 2, 5, 6, 7, 4, 2, 1, 3, 2, 2, 2, 1, 1, 1, 4, 3, 2, 2, 1, 1, 2, 2, 3, 4, 5, 232, 2, 1, 1, 23, 3, 4, 2, 1, 1, 2};
        ArraySorting.qSort(arr, 0, arr.length - 1);
        int[] out = find(arr, 10);
        if (out != null && out.length > 0) {
            System.out.println(Arrays.toString(out));
        }
    }
}
