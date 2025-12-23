package org.example.Multithreading;

public class SynchronizatiomDemo {
    private static int counter=0;
    public static void main(String args[]) throws InterruptedException {
        Thread one = new Thread(()->{
            for (int i=0;i<100;i++){
               // counter++; use synchrnized key word
                increment();
            }
        });

        Thread two = new Thread(()->{
            for (int i=0;i<100;i++){
                // counter++; use synchrnized key word
                increment();
            }
        });

        one.start();
        two.start();
        one.join();
        two.join();
        System.out.println("Counter value"+counter);
    }

    private synchronized static void increment() {
        counter++;
    }
}
