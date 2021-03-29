package com.tangp.algo.pieces;

/**
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public class RemoveArrayElements {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3, len = removeElement(nums, val);
        System.out.println("Elements left:" + len);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }

    public static int removeElement(int[] nums, int val) {
        int i = 0, j = nums.length - 1, len = nums.length;
        for (; i < nums.length && i <= j; i++) {
            if (nums[i] == val) {
                len--;
                for (; j > i; j--) {
                    if (nums[j] != val) {
                        swap(nums, i, j);
                        j--;
                        break;
                    } else {
                        len--;
                    }
                }
            }
        }
        return len;
    }

    private static void swap(int[] nums, int i, int j) {
        int val = nums[i];
        nums[i] = nums[j];
        nums[j] = val;
    }
}
