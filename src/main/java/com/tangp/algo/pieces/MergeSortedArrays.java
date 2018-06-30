package com.tangp.algo.pieces;

/**
 * @author pengtang
 * <p>
 * 给出一个sorted array A and B, 并且B的长度是A的两倍，B组数列后半个组是空的。要求把这2个merge起来，sorted，并且不要extra space.
 */
public class MergeSortedArrays {


    /**
     * 假设A和B均为升序排列
     *
     * @param arrA
     * @param arrB
     * @return
     */
    public static int[] merge(int[] arrA, int[] arrB) {
        if (arrA == null || arrB == null || arrA.length * 2 != arrB.length) {
            throw new IllegalArgumentException("You might want to check inputs.");
        }

        int indexA = 0;
        for (int indexB = 0; indexA < arrA.length && indexB < arrB.length; indexB++) {
            if (arrA[indexA] <= arrB[indexB]) {
                for (int i = arrB.length - 1; i > indexB; i--) {
                    arrB[i] = arrB[i - 1];
                }
                arrB[indexB] = arrA[indexA];
                indexA++;
            }
        }

        //说明A中还有部分元素未拷贝到B中
        if (indexA < arrA.length - 1) {
            for (; indexA < arrA.length; indexA++) {
                arrB[arrA.length + indexA] = arrA[indexA];
            }
        }

        return arrB;
    }

    public static void main(String[] args) {
        int[] arrA = new int[]{1, 2, 3, 5, 7, 9}, arrB = new int[arrA.length * 2];
        arrB[0] = 1;
        arrB[1] = 1;
        arrB[2] = 1;
        arrB[3] = 2;
        arrB[4] = 2;
        arrB[5] = 4;

        int[] out = merge(arrA, arrB);
        for (int i : out) {
            System.out.println(i);
        }
    }
}
