package com.tangp.patterns;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 单例模式的多种实现方式；
 */
public class Singleton {

    /**
     * 饿汉模式
     * 用静态属性来保证线程安全
     */
    public static Singleton SINGLETON = new Singleton();

    /**
     * 私有的构造函数保证了仅能通过类属性SINGLETON才能获取到类实例；
     */
    private Singleton() {

    }

    /**
     * 懒汉模式；
     * 只有应用中需要使用实例的时候，再去初始化；
     * <p>
     * don't write codes like this!!
     *
     * @return
     */
    public static Singleton getInstance() {
        if (null == SINGLETON) {
            synchronized (new Object()){
                SINGLETON = new Singleton();
            }
        }
        return SINGLETON;
    }

    private static final AtomicReference<Singleton> SINGLETON_ATOMIC_REFERENCE
            = new AtomicReference<>();


    /**
     * 半线程安全
     *
     * @return
     */
    public static Singleton getInstanceThreadSafe() {
        if (null == SINGLETON_ATOMIC_REFERENCE.get()) {
            SINGLETON_ATOMIC_REFERENCE.compareAndSet(null, new Singleton());
        }
        return SINGLETON_ATOMIC_REFERENCE.get();
    }

    /**
     * 完全线程安全
     *
     * @return
     */
    public static Singleton getInstanceFullThreadSafe() throws InterruptedException {
        if (null == SINGLETON_ATOMIC_REFERENCE.get()) {
            // 其他线程可能正在初始化实例；
            Thread.sleep(new Random().nextInt(300));

            synchronized (Singleton.class) {
                //Double Check Locking 双重检查锁（设计模式的一种）
                if (null == SINGLETON_ATOMIC_REFERENCE.get()) {
                    SINGLETON_ATOMIC_REFERENCE.compareAndSet(null, new Singleton());
                }
            }
        }
        return SINGLETON_ATOMIC_REFERENCE.get();
    }

    /**
     * 使用枚举的方式来构造单例
     */
    enum SingletonEnum {
        instance;

        private final Singleton inner;

        SingletonEnum() {
            this.inner = new Singleton();
        }

        public static Singleton getSingleton() {
            return instance.inner;
        }
    }
}
