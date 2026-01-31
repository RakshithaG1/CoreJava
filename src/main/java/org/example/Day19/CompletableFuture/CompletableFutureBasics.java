package org.example.Day19.CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureBasics {

    public static void main(String[] args) throws Exception {

        // 1. Completed future (already has result)
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("Hello");
        System.out.println(future1.get());  // Hello

        // 2. Run async task
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Running in background thread Future 2");
        });
        System.out.println("Is main Thread waiting for Thread Future 2 in background thread");

        future2.join();  // Wait for completion (non-blocking alternative to get)

        // 3. Supply async result
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Computing result...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42;
        });
//        System.out.println("Result: " + future3.get());
        future3.thenAccept(result ->
                System.out.println("Future 3 Result: " + result)
        );

        System.out.println("Is main thread block after future 3");


        // 4. Failed future
        CompletableFuture<Integer> future4 = new CompletableFuture<>();
        future4.completeExceptionally(new RuntimeException("Error!"));
        try {
            future4.get();
            System.out.println("Is main thread block after future 4");
        } catch (ExecutionException e) {
            System.out.println("Exception: " + e.getCause().getMessage());
        }
    }
}
