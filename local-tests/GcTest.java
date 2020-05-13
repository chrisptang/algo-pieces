import java.lang.ref.WeakReference;
import java.util.Scanner;

public class GcTest {

    private static volatile boolean minorGcOccurred = false;

    private static volatile boolean fullGcOccurred = false;

    private static volatile int count = 30;

    public static void main(String[] args) throws Exception {
        System.out.println("\n\nStart....");
        WeakReference<?> monitor = new WeakReference<>(new FooClass(1));
        while (monitor.get() != null && --count > 0) {
            new FooClass(1);
            System.out.println("Creating MinorGC Garbege....");
        }

        monitor = new WeakReference<>(new FooClass(1));
        while (monitor.get() != null && --count > 0) {
            new FooClass(10);
            System.out.println("Creating FullGC Garbege....");
        }
        System.out.println("\n\nEnd....");
    }

    public static class FooClass {
        private final int[] values;

        public FooClass(int multipler) {
            assert multipler > 0 && multipler < Integer.MAX_VALUE / 26214;
            this.values = new int[multipler * 20000];
        }
    }

    public static class MinorGcMonitor {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MinorGcMonitor has been cleaned");
        }
    }

    public static class FullGcMonitor {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("FullGcMonitor has been cleaned");
        }
    }
}