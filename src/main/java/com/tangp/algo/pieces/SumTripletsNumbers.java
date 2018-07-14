package com.tangp.algo.pieces;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 */

public class SumTripletsNumbers {

    /**
     * 穷举法，时间复杂度最大的方法；
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        long started = System.currentTimeMillis();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0, size = nums.length; i < size; i++) {
            int subSearching = 0 - nums[i];
            for (int j = 0; j < size && j != i; j++) {
                int thirdLevelSearching = subSearching - nums[j];
                for (int k = 0; k < size && k != i && k != j; k++) {
                    if (thirdLevelSearching == nums[k]) {
                        List<Integer> ret = new ArrayList<>();
                        ret.add(nums[i]);
                        ret.add(nums[j]);
                        ret.add(nums[k]);

                        Collections.sort(ret);
                        result.add(ret);
                    }
                }
            }
        }

        if (result.size() <= 1) {
            return result;
        }
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (List<Integer> list : result) {
            String key = list.stream().map(it -> it.toString()).collect(Collectors.joining(","));
            resultMap.put(key, list);
        }
        System.out.println("Time cost:" + (System.currentTimeMillis() - started));
        return new ArrayList<>(resultMap.values());
    }

    public static List<List<Integer>> threeSum3(int[] num) {
        long started = System.currentTimeMillis();
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        System.out.println("Time cost:" + (System.currentTimeMillis() - started));
        return res;
    }

    public static void main(String[] args) {
        int[] integers = new int[]{-3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, 123, 442, 1232, 4, 53, 12312, 312, 4342, 123, 1, 2, 3, 2, 453, 2, 12, 332, 222, 1, 123, 412, 4, 12, 4123412, 4, 123, 12, 98, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, -3, -3, 5, 2, 3, 2, 1, 4, 1, 2, 3, 5, 6, -1, 0, 6, -4, -6, -8, -5, 1, 2, 3, 123, 442, 1232, 4, 53, 12312, 312, 4342, 123, 1, 2, 3, 2, 453, 2, 12, 332, 222, 1, 123, 412, 4, 12, 4123412};
        List<List<Integer>> result = threeSum(integers);
        System.out.println(result);
        System.out.println("Another result");
        result = threeSum3(integers);
        System.out.println(result);
    }
}
