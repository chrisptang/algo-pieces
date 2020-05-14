package com.tangp.algo.pieces;

import com.tangp.algo.pieces.utils.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 编号0~N 个线程循环打印 0~timesToPrint；
 */
public class MultiThreadPrinting {

    private static volatile CountDownLatch COUNT_DOWN_LATCH;

    public static void main(String[] args) throws Exception {

        System.out.println("printing 0~100 with 3 threads...");
        multiThreadPrinting(3, 100);
        Thread.sleep(1000L);


        System.out.println("printing 0~100 with 5 threads...");
        COUNT_DOWN_LATCH = new CountDownLatch(5);
        final Stopwatch stopwatch = Stopwatch.createAndStart();
        multiThreadPrinting(5, 100);
        COUNT_DOWN_LATCH.await();
        stopwatch.stop();
        System.out.println("Total cost:" + stopwatch.getDuration());
        Thread.sleep(1000L);

        System.out.println("printing 0~100 with 5 circled threads...");
        COUNT_DOWN_LATCH = new CountDownLatch(5);
        final Stopwatch stopwatch2 = Stopwatch.createAndStart();
        printWithCircledThread(5, 100);
        COUNT_DOWN_LATCH.await();
        stopwatch2.stop();
        System.out.println("Total cost:" + stopwatch2.getDuration());
    }

    protected static void multiThreadPrinting(final int n, final int timesToPrint) {
        final AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < n; i++) {
            new PrintingThread(i, n, counter, timesToPrint).start();
        }
    }

    private static class PrintingThread extends Thread {

        protected final int index;

        protected final int threadCount;

        private final AtomicInteger counter;

        private final int timesToPrint;

        public PrintingThread(int i, int threadCount, AtomicInteger counter, int timesToPrint) {
            this.index = i;
            this.threadCount = threadCount;
            this.counter = counter;
            this.timesToPrint = timesToPrint;
            this.setName("thread:" + this.index);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (counter) {
                        if (counter.get() >= timesToPrint) {
                            counter.notifyAll();
                            if (null != COUNT_DOWN_LATCH) {
                                COUNT_DOWN_LATCH.countDown();
                            }
                            return;
                        }
                        while (counter.get() % threadCount != this.index && counter.get() <= timesToPrint) {
                            counter.wait();
                        }
                        if (counter.get() <= timesToPrint) {
                            System.out.println(this.getName() + " " + counter.getAndIncrement());
                        }
                        counter.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected static void printWithCircledThread(final int threads, final int timesToPrint) {
        Semaphore first = new Semaphore(1), last = null, previous = first;

        final AtomicInteger counter = new AtomicInteger(0);

        Thread[] allThreads = new Thread[threads];

        // 构建一个信号量的环；
        // 相比前一种算法，并发锁竞争少；
        for (int index = 0; index < threads; index++) {
            last = new Semaphore(0);
            if (index < threads - 1) {
                allThreads[index] = new CirclePrintThread(previous, last, timesToPrint, index, counter);
                previous = last;
            } else {
                allThreads[index] = new CirclePrintThread(previous, first, timesToPrint, index, counter);
            }
        }
        for (Thread thread : allThreads) {
            thread.start();
        }
    }

    protected static class CirclePrintThread extends Thread {
        private final Semaphore mySemaphore;
        private final Semaphore nextSemaphore;
        private final int timesToPrint;
        private final AtomicInteger counter;

        public CirclePrintThread(Semaphore mySemaphore, Semaphore nextSemaphore, int timesToPrint, int index, AtomicInteger counter) {
            this.timesToPrint = timesToPrint;
            this.mySemaphore = mySemaphore;
            this.nextSemaphore = nextSemaphore;
            this.setName("Thread:" + index);
            this.counter = counter;
        }

        @Override
        public void run() {
            while (counter.get() <= timesToPrint) {
                try {
                    mySemaphore.acquire();
                    if (counter.get() <= this.timesToPrint) {
                        System.out.println(this.getName() + " " + counter.getAndIncrement());
                    }
                    nextSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (null != COUNT_DOWN_LATCH) {
                COUNT_DOWN_LATCH.countDown();
            }
        }
    }
}
