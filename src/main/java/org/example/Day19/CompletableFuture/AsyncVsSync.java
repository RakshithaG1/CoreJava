package org.example.Day19.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class AsyncVsSync {

    public static void main(String[] args) throws Exception {

        // SYNC: Uses same thread
        System.out.println("Main thread: " + Thread.currentThread().getName());

        CompletableFuture<String> sync = CompletableFuture.supplyAsync(() -> {
            System.out.println("  Supply thread: " + Thread.currentThread().getName());
            return "Sync result";
        }).thenApply(result -> {
            // Executes in SAME thread as supply (or main)
            System.out.println("  Then thread: " + Thread.currentThread().getName());
            return result.toUpperCase();
        });

        sync.join();

        // ASYNC: Uses different thread
        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
            System.out.println("  Supply thread: " + Thread.currentThread().getName());
            return "Async result";
        }).thenApplyAsync(result -> {
            // Executes in DIFFERENT thread from supply
            System.out.println("  Then thread (async): " + Thread.currentThread().getName());
            return result.toUpperCase();
        });

        async.join();
    }
}
