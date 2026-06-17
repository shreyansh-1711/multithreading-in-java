package com.example.synchornised;

public class static_synchronisation {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter counterOne = new SharedCounter();
        SharedCounter counterTwo = new SharedCounter();

        Thread threadOne = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                counterOne.increment();
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                counterTwo.increment();
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("Final count: " + SharedCounter.getCount());
    }
}

class SharedCounter {
    private static int count = 0;

    static synchronized void increment() {
        count++;
    }

    static synchronized int getCount() {
        return count;
    }
}
