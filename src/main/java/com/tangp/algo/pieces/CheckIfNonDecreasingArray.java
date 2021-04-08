package com.tangp.algo.pieces;

/**
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
 * <p>
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2)
 */
public class CheckIfNonDecreasingArray {
    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{4, 2, 3}));
        System.out.println(checkPossibility(new int[]{4, 2, 1}));
        System.out.println(checkPossibility(new int[]{3, 4, 2, 3}));
        System.out.println(checkPossibility(new int[]{-1, 4, 2, 3}));
        System.out.println(checkPossibility(new int[]{5, 7, 1, 8}));
    }

    public static boolean checkPossibility(int[] nums) {
        return checkPossibility(nums, true);
    }


    /**
     * @param nums 递归法，尝试修改3种情况；
     * @param isFirstRun
     * @return
     */
    private static boolean checkPossibility(int[] nums, boolean isFirstRun) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (!isFirstRun) {
                    return false;
                }
                int i1 = nums[i - 1], i0 = nums[i], i2 = nums[i >= 2 ? i - 2 : i - 1];
                //尝试把i-1改成i-2大小；
                nums[i - 1] = i2;
                boolean checkI12 = checkPossibility(nums, false);
                //尝试把i-1改成i大小；
                nums[i - 1] = i0;
                boolean checkI10 = checkPossibility(nums, false);
                //尝试把i改成i-1大小；
                nums[i - 1] = i1;
                nums[i] = i1;
                boolean checkI01 = checkPossibility(nums, false);
                return checkI12 || checkI10 || checkI01;
            }
        }
        return true;
    }
}
