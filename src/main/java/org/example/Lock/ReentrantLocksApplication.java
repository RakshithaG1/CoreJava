package org.example.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ThreadSafeCounter {
    int count = 0;
    //✔ Explicit lock/unlock
    //✔ More flexible than synchronized
    ReentrantLock lock = new ReentrantLock();

    void increment() {
        lock.lock();
        try { count++; }
        finally { lock.unlock(); }
    }

    int get() {
        lock.lock();
        try { return count; }
        finally { lock.unlock(); }
    }
}

//🔹 When to use ReadWriteLock
//
//✔ Read-heavy systems
//✔ Cache, config, repository
class ConcurrentCache<K,V> {
    Map<K,V> map = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    V get(K k) {
        lock.readLock().lock();
        try { return map.get(k); }
        finally { lock.readLock().unlock(); }
    }

    void put(K k, V v) {
        lock.writeLock().lock();
        try { map.put(k,v); }
        finally { lock.writeLock().unlock(); }
    }
}

public class ReentrantLocksApplication {
}
