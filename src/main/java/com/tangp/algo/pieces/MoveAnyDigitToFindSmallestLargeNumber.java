package com.tangp.algo.pieces;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有一个超大数N由[n1,n2,n3,n4...]组成，可以任意交换其两位数字n0', n0'，可以组成新的数N'，
 * 找到比当前数N大的最小的N'；
 */
public class MoveAnyDigitToFindSmallestLargeNumber {
    public static void main(String[] args) {
        String veryLargeNum = "1348769234315865320";
        System.out.println(veryLargeNum);
        System.out.println(moveAnyDigitsToFindSmallestLargerNumber(veryLargeNum));
    }

    public static String moveAnyDigitsToFindSmallestLargerNumber(String veryLargeNumber) {
        if (null == veryLargeNumber || veryLargeNumber.length() <= 1) {
            return veryLargeNumber;
        }
        int[] digits = new int[veryLargeNumber.length()];
        AtomicInteger index = new AtomicInteger(0);
        for (String digitStr : veryLargeNumber.split("")) {
            digits[index.getAndIncrement()] = Integer.valueOf(digitStr);
        }
        int[] digitsToBeenMoved = findDigitsToMove(digits, 0, digits.length - 1);
        if (digitsToBeenMoved != null && digitsToBeenMoved.length == 2) {
            swap(digits, digitsToBeenMoved[0], digitsToBeenMoved[1]);
            StringBuilder stringBuilder = new StringBuilder();
            for (int digit : digits) {
                stringBuilder.append(digit);
            }
            return stringBuilder.toString();
        } else {
            return veryLargeNumber;
        }
    }

    protected static int[] findDigitsToMove(int[] digits, int initialLeftBoundary, int initialLowerPointerToBeenMove) {
        if (digits == null || 1 > (initialLowerPointerToBeenMove - initialLeftBoundary)) {
            return null;
        }
        for (int j = initialLowerPointerToBeenMove - 1; j >= initialLeftBoundary; j--) {
            if (digits[j] < digits[initialLowerPointerToBeenMove]) {
                // found
                int[] tempFound = new int[]{j, initialLowerPointerToBeenMove};
                int[] foundAgain = findDigitsToMove(digits, j + 1, initialLowerPointerToBeenMove - 1);
                if (null != foundAgain && foundAgain.length == 2) {
                    return foundAgain;
                } else {
                    return tempFound;
                }
            }
        }
        // not found
        return findDigitsToMove(digits, initialLeftBoundary, initialLowerPointerToBeenMove - 1);
    }

    protected static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
