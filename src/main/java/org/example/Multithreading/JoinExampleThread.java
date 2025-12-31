package org.example.Multithreading;

public class JoinExampleThread {
    public static void main(String args[]) {
        Thread thread1 = new Thread(() ->{
            for(int i=0;i<5;i++) {
                System.out.println("Thread1 =="+i);
            }
        });

        Thread thread2 = new Thread(() ->{
            for(int i=0;i<25;i++) {
                System.out.println("Thread2 =="+i);
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("Threads execution in progress");

//            thread1.join();
//            thread2.join();
        System.out.println("Done exceuting all threads");
        //with join CacheApp threads wait for cmpletion of Thread1 and Thread2
        //without above join print statement wil be printed
        //wait Till completion of that thread instructing the parent Thread or main thraed -->menaing of thread.join()
    }
}
