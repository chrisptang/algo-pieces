package com.tangp.algo.pieces;

/**
 * 长度为n的正整数，保留k位，使得结果最小，需要保留位的顺序；
 */
public class TrimmingInteger {


    public static void main(String[] args) {
        String num = "31241241";
        int k = 4;
        System.out.println(findSmallestSubNumber(num, num.length() - k));
        num = "31241245";
        System.out.println(findSmallestSubNumber(num, num.length() - k));
    }

    //思路：贪心算法：先删除1位，使得留下了的数最小，然后再删除1位，直到删除n-k位，得到的就是最小的数；
    //从左到右找第一次出现比后面大的数，然后删除
    //算法复杂度：O(n*(n-k))
    public static String findSmallestSubNumber(String number, int k) {
        if (number == null || k <= 0 || number.length() <= k) {
            return number;
        }
        for (int i = 0; i < number.length() - 1; i++) {
            int num1 = Integer.valueOf(String.valueOf(number.charAt(i))),
                    num2 = Integer.valueOf(String.valueOf(number.charAt(i + 1)));
            if (num1 > num2) {
                return findSmallestSubNumber(number.substring(0, i) + number.substring(i + 1), k - 1);
            }
        }
        return number.substring(0, number.length() - 1);
    }
}
