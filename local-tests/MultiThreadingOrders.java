import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadingOrders {
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
