package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.single.example;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {

    private static class CounterHolder {

        private static final Counter counter = new Counter();
    }

    private Counter() {
    }

    public static final Counter getInstance() {
        return CounterHolder.counter;
    }

    private AtomicLong online = new AtomicLong();

    public long getOnline() {
        return online.get();
    }

    public long add() {
        return online.incrementAndGet();
    }
}