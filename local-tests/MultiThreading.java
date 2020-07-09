import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreading {

    private static int globe2 = 0;

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println("ReaderThread running..");
                Thread.yield();
                System.out.println(number);
            }
        }
    }

    public static int addOne() {
        int tmp = globe2;
        tmp = tmp + 1;
        globe2 = tmp;
        return tmp;
    }

    public static void main(String[] args) throws InterruptedException {
        new WorkingThread().start();
        new WorkingThread().start();

        Thread.sleep(300L);
        System.out.println(globe2);

        new ReaderThread().start();
        number = 42;
        Thread.yield();
        ready = true;
    }

    private static class WorkingThread extends Thread {
        private static final AtomicInteger INDEX = new AtomicInteger(0);

        private final AtomicInteger counting = new AtomicInteger(0);

        public WorkingThread() {
            this.setName("Thread:" + INDEX.getAndIncrement());
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(String.format("%s %d - %d", Thread.currentThread().getName(), counting.getAndIncrement(), addOne()));
            }
        }
    }
}
