package org.example.Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

//Atomic Variable
//whats read-modify write
//Different types of atomic variable
//AtomicInteger
//AtomicBoolean
//AtomicLong
//Basic Operator
//get()
//set()
//compareAndSet(expected, update)
//getAndIncrement() / incrementAndGet()
//getAndDecrement() / decrementAndGet()
public class AtomicVariableDemo {
    private static final int count = 0;
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });

        Thread two = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });

        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("Counter value using AtomicInteger=="+counter);
    }

}
