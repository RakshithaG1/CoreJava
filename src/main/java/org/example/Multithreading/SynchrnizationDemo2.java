package org.example.Multithreading;

public class SynchrnizationDemo2 {
    private static int counter1=0;
    private static int counter2=0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String args[]) {
        Thread one = new Thread(() -> {
            for(int i=0;i<100;i++) {
                increment1(counter1);
                System.out.println("Thread1 Printing =="+counter1);
            }
        });

        Thread two = new Thread(() -> {
            for(int i=0;i<100;i++) {
                increment2(counter2);
                System.out.println("Thread2 Printing =="+counter2);
            }
        });

        one.start();
        two.start();
    }

    static void increment1(int counter){
        synchronized(lock1) {
            counter1++;
        }
    }

    static void increment2(int counter){
        synchronized(lock2) {
            counter2++;
        }
    }
}
