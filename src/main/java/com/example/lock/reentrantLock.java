package com.example.lock;

import java.util.concurrent.locks.ReentrantLock;

public class reentrantLock {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread threadOne = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                counter.increment();
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                counter.increment();
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("Final count: " + counter.getCount());
    }
}

class Counter {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    int getCount() {
        return count;
    }
}
