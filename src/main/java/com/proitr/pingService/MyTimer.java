package com.proitr.pingService;

/**
 * Created by kondakov on 29.10.2015.
 */
public class MyTimer {
    private final long start;

    public MyTimer() {
        super();
        start = System.currentTimeMillis();
    }

    public long getElapsed() {
        return System.currentTimeMillis() - start;
    }
}
