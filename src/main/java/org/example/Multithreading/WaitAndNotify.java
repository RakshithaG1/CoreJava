package org.example.Multithreading;

public class WaitAndNotify {
    //LOCK has been accquired at class Level
    private static final Object LOCK = new Object();
    public static void main(String args[]) throws InterruptedException {
        Thread thread1= new Thread(() -> {
                try {
                    one();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        });

        Thread thread2= new Thread(() -> {
                try {
                    two();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }

    public static void one() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Hello from One ===");
            LOCK.wait();
            System.out.println("Back again in the method one");
        }
    }

    public static void two() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Hello from Two ===");
            LOCK.notify();
            System.out.println("Hello again from Two after calling notify===");
        }
    }


}
