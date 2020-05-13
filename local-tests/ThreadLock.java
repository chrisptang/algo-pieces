import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLock {

    private static volatile int counting = 0;

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws Exception {
        AtomicInteger threadCounter = new AtomicInteger(0);
        new WaitThread(threadCounter.getAndIncrement()).start();
        new WaitThread(threadCounter.getAndIncrement()).start();
        new WaitThread(threadCounter.getAndIncrement()).start();
        new WaitThread(threadCounter.getAndIncrement()).start();

        new WakeupThread().start();

        Thread.sleep(3000L);
    }

    private static void increaseCount() {
        synchronized (LOCK) {
            if (counting <= 5) {
                System.out.println(Thread.currentThread().getName() + " About to wait... " + counting);
                try {
                    LOCK.wait();
                    System.out.println(Thread.currentThread().getName() + " Wait ended... " + counting);
                    counting = counting + 1;
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " Wait exception popped... " + counting);
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " About to increase counting... " + counting);
                counting = counting + 1;
            }
        }
    }

    private static class WaitThread extends Thread {

        private final int namedCode;

        public WaitThread(int namedCode) {
            this.namedCode = namedCode;
            setName("wait-thread:" + this.namedCode);
        }

        @Override
        public void run() {
            increaseCount();
        }
    }

    private static class WakeupThread extends Thread {

        public WakeupThread() {
            setName("wakeup-thread:");
        }

        @Override
        public void run() {
            try {
                synchronized (LOCK) {
                    Thread.sleep(1500L);
                    System.out.println(Thread.currentThread().getName() + " About to wakeup all... " + counting);
                    counting = 5;
                    LOCK.notifyAll();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}