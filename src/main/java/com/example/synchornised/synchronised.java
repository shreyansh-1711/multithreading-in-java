package com.example.synchornised;

public class synchronised {
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
    private int count = 0;

    synchronized void increment() {
        count++;
    }

    int getCount() {
        return count;
    }
}
