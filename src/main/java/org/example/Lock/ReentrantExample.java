package org.example.Lock;

import java.util.concurrent.locks.ReentrantLock;

//ReentrantLock Features1. Reentrancy
public class ReentrantExample {
    private ReentrantLock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method - Lock acquired");
            innerMethod();  // Same thread can acquire lock again
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner method - Lock acquired again (reentrant)");
        } finally {
            lock.unlock();
        }
    }
}