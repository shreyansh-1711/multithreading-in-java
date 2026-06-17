package com.example.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readWriteLock {
    public static void main(String[] args) throws InterruptedException {
        SharedData sharedData = new SharedData();

        Thread writer = new Thread(() -> {
            sharedData.write(100);
        });

        Thread readerOne = new Thread(() -> {
            sharedData.read();
        });

        Thread readerTwo = new Thread(() -> {
            sharedData.read();
        });

        writer.start();
        writer.join();

        readerOne.start();
        readerTwo.start();

        readerOne.join();
        readerTwo.join();
    }
}

class SharedData {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int value = 0;

    void write(int newValue) {
        lock.writeLock().lock();
        try {
            System.out.println("Writing value: " + newValue);
            value = newValue;
        } finally {
            lock.writeLock().unlock();
        }
    }

    void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read value: " + value);
        } finally {
            lock.readLock().unlock();
        }
    }
}
