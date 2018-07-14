package com.tangp.algo.pieces;

/**
 * @author pengtang
 * <p>
 * 给出一个sorted array A and B, 并且B的长度是A的两倍，B组数列后半个组是空的。要求把这2个merge起来，sorted，并且不能使用extra space.
 */
public class MergeSortedArrays {

    /**
     * 假设A和B均为升序排列；
     *
     * 核心思想：
     * 逐个比较A和B中的元素，满足条件则将B中所有的元素往后移动一个位置，再将A的元素插入B中；最终返回B作为merge的结果；
     *
     * 存在的问题：性能问题，最坏的情况将发生n(n-1)/2次移动，时间复杂度为O(n*n);最后的情况仅需移动N次；
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

    /**
     * 第二种实现方式:
     * 先将B中的前半部分元素移动到后半部，空出前半部空间；然后依次对比A和B后半部的元素的大小，将较小值者插入到B的前半部；
     *
     * 算法总共需要移动2*arrA.length次；性能较实现1有大幅提高；
     *
     * @param arrA
     * @param arrB
     * @return
     */
    public static int[] merge2(int[] arrA, int[] arrB) {
        if (arrA == null || arrB == null || arrA.length * 2 != arrB.length) {
            throw new IllegalArgumentException("You might want to check inputs.");
        }
        for (int indexB = 0; indexB < arrA.length; indexB++) {
            //先将B中的前半部分元素移动到后半部，空出前半部空间；
            arrB[indexB + arrA.length] = arrB[indexB];
        }
        for (int mergeIndex = 0, indexA = 0, indexB = arrA.length; mergeIndex < arrB.length; mergeIndex++) {
            if (indexA < arrA.length
                && (indexB >= arrB.length || arrA[indexA] <= arrB[indexB])) {
                //如果A的元素还未移动完成，且A的元素比B后半部的元素值小，则插入至结果中，同时从下一个A元素开始查找；
                arrB[mergeIndex] = arrA[indexA++];
            } else if (indexB < arrB.length) {
                //如果B的元素还未移动完成，且B的元素比A的元素值小，则插入至结果中，同时从下一个B元素开始查找；
                arrB[mergeIndex] = arrB[indexB++];
            }
        }

        return arrB;
    }

    public static void main(String[] args) {
        int[] arrA = new int[] {1, 2, 3, 5, 7, 9}, arrB = new int[arrA.length * 2];
        arrB[0] = 1;
        arrB[1] = 1;
        arrB[2] = 2;
        arrB[3] = 2;
        arrB[4] = 4;
        arrB[5] = 6;

        int[] out = merge2(arrA, arrB);
        for (int i : out) {
            System.out.println(i);
        }
    }
}
