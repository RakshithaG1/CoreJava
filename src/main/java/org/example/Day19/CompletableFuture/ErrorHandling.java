package org.example.Day19.CompletableFuture;


import java.util.concurrent.CompletableFuture;

//Error Handling Methods:
//exceptionally() - Recover with default value
//handle() - Process both success and error
//whenComplete() - Side effects on completion
public class ErrorHandling {

    public static void main(String[] args) throws Exception {

        // 1. exceptionally() - Recover from exception
        CompletableFuture<Object> future1 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Something went wrong!");
        }).exceptionally(ex -> {
            System.out.println("Caught: " + ex.getMessage());
            return -1;  // Default value
        });

        System.out.println("Result: " + future1.get());  // Result: -1

        // 2. handle() - Process both result and exception
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                return "Success";
            } else {
                throw new RuntimeException("Random failure");
            }
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Error: " + ex.getMessage());
                return "Failed";
            } else {
                System.out.println("Success: " + result);
                return result;
            }
        });

        System.out.println(future2.get());

        // 3. whenComplete() - Side effect after completion
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Data")
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Completed with: " + result);
                    } else {
                        System.out.println("Failed with: " + ex.getMessage());
                    }
                });

        future3.join();
    }
}

