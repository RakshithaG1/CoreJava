package org.example.Day19.CompletableFuture;

import java.util.concurrent.CompletableFuture;

//Transformation Methods:
//thenApply(Function) - Transform result to new type
//thenAccept(Consumer) - Consume result (no return)
//thenRun(Runnable) - Execute after (ignore result)
public class ChainedOperations {

    public static void main(String[] args) {

        CompletableFuture<Void> future =
                CompletableFuture.supplyAsync(() -> {
                            System.out.println("Step 1: Fetching user data");
                            return "Alice";
                        })

                        // Chain 1: Transform result
                        .thenApply(name -> {
                            System.out.println("Step 2: Processing - " + name);
                            return name.toUpperCase();
                        })

                        // Chain 2: Transform to different type
                        .thenApply(name -> {
                            System.out.println("Step 3: Creating message");
                            return "Hello, " + name + "!";
                        })

                        // Chain 3: Terminal operation (consume result)
                        .thenAccept(message -> {
                            System.out.println("Step 4: " + message);
                        });

        // Wait for async pipeline to finish
        future.join();
    }
}