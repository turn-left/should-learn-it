package com.ethen.concurrency.keywords;

public class SynchronizedDemo {
    static int count = 0;

    public synchronized void doGet() {
        System.err.println("synchronized doGet ...");
    }

    public int syncSelfCount() {
        synchronized (this) {
            return count++;
        }
    }
}
