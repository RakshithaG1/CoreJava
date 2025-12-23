package org.example.Multithreading;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer {

    public static void main(String[] args) {
        Worker worker = new Worker(5);

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}

class Worker {
    private int sequence = 0;
    private final int capacity;
    private final List<Integer> container = new LinkedList<>();
    private final Object lock = new Object();

    Worker(int capacity) {
        this.capacity = capacity;
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (lock) {

                while (container.size() == capacity) {
                    System.out.println("Container is full... Producer waiting.");
                    lock.wait();
                }

                System.out.println("Produced: " + sequence);
                container.add(sequence++);
                lock.notifyAll();
            }

            Thread.sleep(500);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {

                while (container.isEmpty()) {
                    System.out.println("Container is empty... Consumer waiting.");
                    lock.wait();
                }

                int removed = container.remove(0);
                System.out.println("Consumed: " + removed);
                lock.notifyAll();
            }

            Thread.sleep(500);
        }
    }
}
