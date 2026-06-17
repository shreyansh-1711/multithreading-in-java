package com.example.synchornised;

public class custom_lock {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();

        Thread userOne = new Thread(() -> account.deposit(1000));
        Thread userTwo = new Thread(() -> account.deposit(1000));

        userOne.start();
        userTwo.start();

        userOne.join();
        userTwo.join();

        System.out.println("Final balance: " + account.getBalance());
    }
}

class BankAccount {
    private final Object lock = new Object();
    private int balance = 0;

    void deposit(int amount) {
        synchronized (lock) {
            for (int i = 1; i <= amount; i++) {
                balance++;
            }
        }
    }

    int getBalance() {
        synchronized (lock) {
            return balance;
        }
    }
}
