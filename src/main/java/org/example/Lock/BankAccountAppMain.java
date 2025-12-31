package org.example.Lock;

//Why Locks?In multithreading, multiple threads accessing shared resources can cause:
//Race conditions
//Data inconsistency
//Unpredictable behavior
//Locks ensure only one thread accesses critical section at a time.ReentrantLockA reentrant mutual exclusion Lock with the same basic behavior as synchronized but with extended capabilities."Reentrant" = Same thread can acquire the same lock multiple times.Basic Structureimport java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance = 1000;
    private ReentrantLock lock = new ReentrantLock();

    // Method 1: Using lock/unlock explicitly
    public void withdraw1(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() +
                        " withdrew $" + amount + ". Balance: $" + balance);
            } else {
                System.out.println("Insufficient balance");
            }
        } finally {
            lock.unlock();  // Always unlock in finally block
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}

// Usage
public class BankAccountAppMain {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Create threads
        Thread t1 = new Thread(() -> account.withdraw1(100), "Thread-1");
        Thread t2 = new Thread(() -> account.withdraw1(150), "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: $" + account.getBalance());
    }
}