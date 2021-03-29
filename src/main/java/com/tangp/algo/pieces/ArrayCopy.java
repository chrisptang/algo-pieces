package com.tangp.algo.pieces;

import java.text.DecimalFormat;

public class ArrayCopy {
    public static void main(String[] args) {
        double invalidDouble = 0D / 0D;
        System.out.println(Double.isNaN(invalidDouble));
        System.out.println(Double.isInfinite(invalidDouble));
        System.out.println(new DecimalFormat("#.###").format(invalidDouble));
    }
}
