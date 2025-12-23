package org.example.Multithreading;

//Lets say 10 thraeds only one thraed gets cpu, so who decidees which thread to run on cpu is Thread Scheduler
//Thread priority 1-10 1 -> highest 10-->low
//higher priotity it gets to run first
//same priority are waiting in FIFO
public class ThreadPriorityExample {
    public  static  void main(String args[]) {
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

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();

}
}