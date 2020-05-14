package com.tangp.algo.pieces.utils;

/**
 * Alternative implementation of com.google.common.base.Stopwatch
 */
public class Stopwatch implements AutoCloseable {

    private volatile long startedAt;

    private volatile long stoppedAt;

    private volatile State state = State.CREATED;

    private static final Object OPERATION_LOCK = new Object();

    private Stopwatch() {

    }

    public void stop() throws IllegalAccessException {
        if (!State.WATCHING.equals(this.state)) {
            throw new IllegalAccessException("this watch is never been started.");
        }
        if (State.STOPPED.equals(this.state)) {
            // ignore this operation because this watch has already been stopped.
            return;
        }
        synchronized (OPERATION_LOCK) {
            this.state = State.STOPPED;
            this.stoppedAt = System.currentTimeMillis();
        }
    }

    public Stopwatch start() {
        if (!State.CREATED.equals(this.state)) {
            // ignore this operation because this watch either been started or stopped.
            return this;
        }
        synchronized (OPERATION_LOCK) {
            this.state = State.WATCHING;
            this.startedAt = System.currentTimeMillis();
            return this;
        }
    }

    public static Stopwatch createAndStart() {
        return new Stopwatch().start();
    }

    public long getDuration() throws IllegalAccessException {
        if (State.STOPPED.equals(this.state)) {
            return this.stoppedAt - this.startedAt;
        }
        throw new IllegalAccessException("this watch is still doing its watching");
    }

    @Override
    public void close() throws Exception {
        this.stop();
    }

    private static enum State {
        CREATED, WATCHING, STOPPED;
    }
}
