package org.example.Lock;

//ReadWriteLockAllows multiple threads to read simultaneously, but only one thread to write (and no reads while writing).Use Case: When reads are frequent and writes are rare.Basic Exampleimport java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {
    private String data = "";
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    // Multiple readers can execute simultaneously
    public String read() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + data);
            Thread.sleep(500);  // Simulate read operation
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    // Only one writer, blocks all readers
    public void write(String newData) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writing: " + newData);
            Thread.sleep(1000);  // Simulate write operation
            data = newData;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}

// Usage
public class CacheApp {
    public static void main(String[] args) {
        Cache cache = new Cache();

        // Create multiple reader threads
        for (int i = 0; i < 5; i++) {
            new Thread(cache::read, "Reader-" + i).start();
        }

        // Create writer thread
        new Thread(() -> cache.write("New Data"), "Writer-1").start();

        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }
}
