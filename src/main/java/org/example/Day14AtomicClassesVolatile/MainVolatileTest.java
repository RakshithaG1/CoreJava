package org.example.Day14AtomicClassesVolatile;

import java.util.Scanner;

//Volatile: Visibility Without Atomicity
class VolatileExample {

    // ❌ WRONG: Without volatile
    private boolean flag = false;
    private int value = 0;

    public void writer() {
        value = 42;
        flag = true;  // May not be visible immediately
    }

    public void reader() {
        while (!flag) {  // Might loop forever!
            // CPU might cache flag value
        }
        System.out.println(value);  // Might be 0!
    }

    // ✅ CORRECT: With volatile
    private volatile boolean flag2 = false;
    private volatile int value2 = 0;

    public void writer2() {
        value2 = 42;
        flag2 = true;  // All threads see this immediately
    }

    public void reader2() {
        while (!flag2) {
            // CPU doesn't cache, always reads from main memory
        }
        System.out.println(value2);  // Guaranteed to be 42!
    }
}

// Real-world example
class VolatileDemo {

    private volatile boolean shutdown = false;

    public void startApplication() {
        Thread worker = new Thread(() -> {
            while (!shutdown) {  // Checks shared memory each iteration
                doWork();
            }
        });
        worker.start();

        Thread monitor = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            shutdown = true;  // All threads immediately see this
        });
        monitor.start();
    }

    private void doWork() {
        System.out.println("Working...");
    }
}
//Volatile Characteristics:
//Guarantees visibility (all threads see latest value)
//Does NOT guarantee atomicity
//Does NOT prevent reordering for dependent operations
//        No locks, very fast
//
//When a variable is declared `volatile`, it essentially
// guarantees that any read will bypass the CPU cache and read directly
// from main memory, and any write will be immediately written to main memory,
// ensuring visibility across all threads.

public class MainVolatileTest {
    public static void main(String[] args) {

        VolatileDemo demo = new VolatileDemo();
        demo.startApplication();
    }
}