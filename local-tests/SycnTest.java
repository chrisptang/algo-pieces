
public class SycnTest {

    private static final Object lock = new Object();

    public static synchronized void sync0() throws Exception {
        System.out.println("Class level lock");
        Thread.sleep(10 * 1000L);
    }

    public static void sync00() {
        System.out.println("No locking method");
    }

    public synchronized void sync1() {
        System.out.println("Instance level lock");
    }

    public void sync2() {
        System.out.println("Block level lock");
        synchronized (lock) {
            System.out.println("Locking object:" + lock);
        }
    }

    public static void main(String[] args) throws Exception{
        SycnTest sycnTest = new SycnTest();
        new Thread(() -> {
            try {
                Thread.sleep(300L);
            } catch (Exception e) {
            }

            System.out.println("Another thread....");
            SycnTest.sync00();
        }).start();

        SycnTest.sync0();
    }
}