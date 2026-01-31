package org.example.Day19.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class CombiningFutures {

    static CompletableFuture<Integer> fetchUserAge(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching age for " + userId);
            return 25;
        });
    }

    static CompletableFuture<String> fetchUserName(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching name for " + userId);
            return "Alice";
        });
    }

    public static void main(String[] args) throws Exception {

        // 1. Combine two futures
        CompletableFuture<String> name = fetchUserName("user1");
        CompletableFuture<Integer> age = fetchUserAge("user1");

        CompletableFuture<String> combined = name.thenCombine(age, (n, a) -> {
            return n + " is " + a + " years old";
        });

        System.out.println(combined.get());
        // Output: Alice is 25 years old

        // 2. Wait for all futures (allOf)
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 30);

        CompletableFuture<Void> allDone = CompletableFuture.allOf(future1, future2, future3);

        allDone.thenRun(() -> {
            int sum = future1.join() + future2.join() + future3.join();
            System.out.println("Sum: " + sum);  // Sum: 60
        }).join();

        // 3. Wait for any future (anyOf)
        CompletableFuture<Object> firstDone = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "1"; }),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "2"; }),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "3"; })
        );

        System.out.println("First done: " + firstDone.join());  // First done: 2
    }
}
