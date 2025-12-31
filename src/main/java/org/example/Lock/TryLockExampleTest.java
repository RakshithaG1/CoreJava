package org.example.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//TryLock - Non-blocking attempt
class TryLockExample {
private ReentrantLock lock = new ReentrantLock();

    // Try to acquire lock without waiting
    public void tryLockMethod() {
        if (lock.tryLock()) {
            try {
                System.out.println("Lock acquired, doing work");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock, moving on");
        }
    }

    // Try with timeout
    public void tryLockWithTimeout() {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println("Lock acquired within timeout");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Timeout: Could not acquire lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Usage
public class TryLockExampleTest {
    public static void main(String[] args) {
        TryLockExample example = new TryLockExample();

        // Start thread 1 that holds lock for 5 seconds
        Thread t1 = new Thread(() -> {
            example.tryLockMethod();
        });

        // Start thread 2 that tries immediately
        Thread t2 = new Thread(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            example.tryLockWithTimeout();  // Will fail
        });

        t1.start();
        t2.start();
    }
}
