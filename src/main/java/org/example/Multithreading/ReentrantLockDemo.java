package org.example.Multithreading;
//ReentrantLockDemo
//Allows multiple threads to accquire lock
//Important  methods
//getHoldCount()
//tryLock
//tryLock(Timeout, timeunits)
//isHeldBycurrentThread


import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private int sharedData = 0;

    public void methodA() {
        lock.lock();
        try{
            sharedData++;
            System.out.println("METHOD A : sharedData"+sharedData);
            //call methodB which also accquires lock
            methodB();
        }
        finally {
            lock.unlock();
        }
    }

    private void methodB() {
        lock.lock();
        try{
            //critical section
            sharedData--;
            System.out.println("METHOD B : sharedData"+sharedData);
            //call methodB which also accquires lock
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String args[]) {

        ReentrantLockDemo demo = new ReentrantLockDemo();

        for (int i=0;i<5;i++) {
            new Thread(demo::methodA).start();
        }
    }
}
