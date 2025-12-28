package org.example.Multithreading;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        // Create a fixed-size thread pool with 3 threads
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);

        // Submit tasks to the pool
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            fixedPool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " executed by " + threadName);
                // Simulate work
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Result of task " + taskId;
            });
        }

        // Proper shutdown
        fixedPool.shutdown(); // Rejects new tasks, but completes queued ones
        try {
            // Wait for all tasks to complete or timeout
            if (!fixedPool.awaitTermination(5, TimeUnit.SECONDS)) {
                // Force shutdown if tasks don't complete in time
                fixedPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            fixedPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks completed");

        // Other types of ExecutorService:

        // Single thread executor
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // Cached thread pool (creates new threads as needed, reuses idle threads)
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        // Scheduled thread pool (for delayed or periodic execution)
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);

        // Remember to shut down all executor services when done
        singleThreadExecutor.shutdown();
        cachedPool.shutdown();
        scheduledPool.shutdown();
    }
}
