package com.tangp.algo.pieces;

import java.util.concurrent.atomic.AtomicInteger;

public class AutoUnboxing {

    public static void main(String[] args) {
        Integer a = 10, b = 20;
        AtomicInteger i = new AtomicInteger(2);
        System.out.println(String.format("a:%d, b:%d", a, b));
        swap(a, b);
        System.out.println(String.format("a:%d, b:%d", a, b));
        System.out.println("AtomicInteger=" + i);
    }

    public static final void swap(Integer a, Integer b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static final void swap2(Integer a, Integer b) {
        Integer temp = a;
        a = b;
        b = temp;
    }
}
