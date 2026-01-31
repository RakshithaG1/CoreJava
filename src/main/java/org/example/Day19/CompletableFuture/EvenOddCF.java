package org.example.Day19.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class EvenOddCF {

    static AtomicInteger counter = new AtomicInteger(0);
    static final int LIMIT = 10;

    public static void main(String[] args) {

        CompletableFuture<Void> even =
                CompletableFuture.runAsync(() -> {
                    while (counter.get() <= LIMIT) {
                        if (counter.get() % 2 == 0) {
                            System.out.println("Even: " + counter.getAndIncrement());
                        }
                    }
                });

        CompletableFuture<Void> odd =
                CompletableFuture.runAsync(() -> {
                    while (counter.get() <= LIMIT) {
                        if (counter.get() % 2 != 0) {
                            System.out.println("Odd : " + counter.getAndIncrement());
                        }
                    }
                });

        CompletableFuture.allOf(even, odd).join();
    }
}
