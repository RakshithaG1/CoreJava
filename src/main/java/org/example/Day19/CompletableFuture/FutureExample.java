package org.example.Day19.CompletableFuture;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit task
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(5000);
            return 42;
        });

        // Wait for result (blocking)
        //Integer result = future.get();  // Blocks here
//        System.out.println("Result: " + result);
        System.out.println("Is it blocked: ");

        // Check if done without blocking
//        if (future.isDone()) {
//            System.out.println("Task completed");
//        }

        //executor.shutdown();
    }
}