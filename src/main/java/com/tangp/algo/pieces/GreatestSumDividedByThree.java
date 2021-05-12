package com.tangp.algo.pieces;

/**
 * 1262. Greatest Sum Divisible by Three
 * https://leetcode-cn.com/problems/greatest-sum-divisible-by-three/
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 * Example 2:
 * <p>
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 * <p>
 * 算法思想：
 * 1:先排序，再用贪心法，从数组最小的数开始找寻，每次移除一个元素，看是否剩下的元素之和能够满足条件；
 */
public class GreatestSumDividedByThree {

    public static void main(String[] args) {
//        System.out.println(maxSumDivThree(new int[]{3, 6, 5, 1, 8}));
        System.out.println(maxSumDivThree(new int[]{2, 6, 2, 2, 7}));
        System.out.println(maxSumDivThree(new int[]{1, 2, 3, 4, 4}));
    }

    public static int maxSumDivThree(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] % 3 == 0 ? nums[0] : 0;
        }
        qSort(nums, 0, nums.length - 1);
        int sumTotal = 0, level = 0, subTotal = 0;
        for (int val : nums) {
            sumTotal += val;
        }
        while (level < nums.length) {
            for (int valToRemove = level; valToRemove < nums.length; valToRemove++) {
                if ((sumTotal - subTotal - nums[valToRemove]) % 3 == 0) {
                    return sumTotal - subTotal - nums[valToRemove];
                }
            }
            subTotal += nums[level];
            level++;
        }
        return 0;
    }

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
}
