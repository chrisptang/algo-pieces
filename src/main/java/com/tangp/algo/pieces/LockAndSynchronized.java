package com.tangp.algo.pieces;

public class LockAndSynchronized {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                synchronized (LOCK) {
                    try {
                        System.out.println("Waiting in thread 1");
                        LOCK.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (LOCK) {
                    System.out.println("Waiting in thread 2");
                    LOCK.notify();
                }
            }
        }.start();
    }
}
