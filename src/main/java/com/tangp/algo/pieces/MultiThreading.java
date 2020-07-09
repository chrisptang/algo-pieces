package com.tangp.algo.pieces;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有三个线程：线程1需要等待线程2&线程3完成工作后进行，且线程1先于 线程2&线程3启动；
 */
public class MultiThreading {
    public static void main(String[] args) throws Exception {

        BlockerThread blockerThread0 = new BlockerThread();
        BlockerThread blockerThread1 = new BlockerThread();

        Thread workingThread = new Thread(() -> {
            System.out.println("working thread started");
            try {
                blockerThread1.joinOnlyStarted();
                blockerThread0.joinOnlyStarted();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("working thread stopped");
        });
        workingThread.start();

        TimeUnit.SECONDS.sleep(1);

        blockerThread1.start();
        blockerThread0.start();
    }

    protected static class BlockerThread extends Thread {

        private static final AtomicInteger THREAD_INDEX = new AtomicInteger(0);

        private final int index;

        private volatile boolean started = false;

        BlockerThread() {
            index = THREAD_INDEX.getAndIncrement();
            this.setName("BlockerThread-" + index);
        }

        @Override
        public synchronized void start() {
            started = true;
            super.start();
        }


        /**
         * 如果线程未开始，则让当前线程等待此线程开始，然后join；
         *
         * @throws InterruptedException
         */
        public void joinOnlyStarted() throws InterruptedException {
            while (!started) {
                //自旋；
                Thread.yield();
            }
            join();
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " running..");
            try {
                TimeUnit.SECONDS.sleep(2 + 2 * index);
                System.out.println(this.getName() + " stopping..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
