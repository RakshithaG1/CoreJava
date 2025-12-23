package org.example.Multithreading;

//How does Cyclic Barrier work under the hood?
//Blocking Queue
//Intr to Blocking Queue
//Blocking Queue Interfaces
//Blocking Queue
//Blocking Deque
//Transfer Queue

//Major Implementations
//ArrayBlocking Queue
//LinkedBlocking Queue
//Priority Blocking Queue
//DelayQueue
//SynchronousQueue

//Blocking Queue Operations
//put(E e)
//take()
//offer(E e)
//poll()
//peek()

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    static  final int Queue_CAPACITY= 10;
    static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(Queue_CAPACITY);
    public static void main(String args[]) {

        //Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i=0;i<= 20;i++) {
                    taskQueue.put(i);
                    System.out.println("Task Produced"+i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //Consumer Thread
        Thread consumerOne = new Thread(() -> {
            try {
                while(true) {
                    int task = taskQueue.take();
                    processTask(task, "ConsumerOne");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        Thread consumerTwo = new Thread(() -> {
            try {
                while(true) {
                    int task = taskQueue.take();
                    processTask(task, "ConsumerTwo");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        producer.start();
        consumerOne.start();
        consumerTwo.start();
    }

    private static void processTask(int task, String consumerName) throws InterruptedException {
        System.out.println("Task being processed By"+consumerName+":"+task);
        Thread.sleep(1000);
        System.out.println("Task consumed By"+consumerName+":"+task);
    }
}

