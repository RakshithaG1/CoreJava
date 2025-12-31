package org.example.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//3. Condition Variablesimport java.util.concurrent.locks.Condition;
public class ProducerConsumer {
    private int value = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // Producer thread
    public void produce(int val) throws InterruptedException {
        lock.lock();
        try {
            while (value != 0) {
                condition.await();  // Wait until value is consumed
            }
            value = val;
            System.out.println("Produced: " + val);
            condition.signalAll();  // Notify waiting threads
        } finally {
            lock.unlock();
        }
    }

    // Consumer thread
    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (value == 0) {
                condition.await();  // Wait until value is produced
            }
            int val = value;
            value = 0;
            System.out.println("Consumed: " + val);
            condition.signalAll();  // Notify waiting threads
            return val;
        } finally {
            lock.unlock();
        }
    }
}