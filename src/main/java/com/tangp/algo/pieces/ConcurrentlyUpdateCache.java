package com.tangp.algo.pieces;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentlyUpdateCache {

    private static final Lock UPDATE_LOCK = new ReentrantLock();

    private static final Condition IS_UPDATING = UPDATE_LOCK.newCondition();

    private static final AtomicReference<Thread> UPDATING_THREAD = new AtomicReference<>();

    private static final HashMap<String, Object> MOCK_CACHE = new HashMap<>();

    /**
     * 模拟获取缓存
     *
     * @return
     */
    private static Object getCache(String key) {
        return MOCK_CACHE.get(key);
    }

    private static Object cacheValueFromDatabase(String key) {
        return "Database-value-for-key:" + key;
    }

    private static Object updateCache(String key) throws Exception {
        boolean locked = UPDATE_LOCK.tryLock(50, TimeUnit.MILLISECONDS);
        Object cacheValue = getCache(key);
        try {
            if (null == UPDATING_THREAD.get()) {
                UPDATING_THREAD.set(Thread.currentThread());
                if (null == cacheValue) {
                    cacheValue = cacheValueFromDatabase(key);
                    MOCK_CACHE.put(key, cacheValue);
                }
            } else {
                IS_UPDATING.await();
            }
        } finally {
            if (locked) {
                UPDATE_LOCK.unlock();
            }
        }

        return null;
    }
}
