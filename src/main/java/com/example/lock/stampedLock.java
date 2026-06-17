package com.example.lock;

import java.util.concurrent.locks.StampedLock;

public class stampedLock {
    public static void main(String[] args) throws InterruptedException {
        Point point = new Point();

        Thread writer = new Thread(() -> {
            point.move(10, 20);
        });

        Thread reader = new Thread(() -> {
            double distance = point.distanceFromOrigin();
            System.out.println("Distance from origin: " + distance);
        });

        writer.start();
        writer.join();

        reader.start();
        reader.join();
    }
}

class Point {
    private final StampedLock lock = new StampedLock();
    private double x;
    private double y;

    void move(double newX, double newY) {
        long stamp = lock.writeLock();
        try {
            x = newX;
            y = newY;
            System.out.println("Point moved to x=" + x + ", y=" + y);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();

        double currentX = x;
        double currentY = y;

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
