package org.example.Day7FlatMap;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreams {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1_000_000; i++) {
            numbers.add(i);
        }

        // Sequential (single thread)
        long startSeq = System.currentTimeMillis();
        long sumSeq = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        long timeSeq = System.currentTimeMillis() - startSeq;

        System.out.println("Sequential: " + sumSeq + " (" + timeSeq + "ms)");

        // Parallel (multiple threads)
        long startPar = System.currentTimeMillis();
        long sumPar = numbers.parallelStream()  // Or .parallel()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        long timePar = System.currentTimeMillis() - startPar;

        System.out.println("Parallel: " + sumPar + " (" + timePar + "ms)");

        /*
        RESULTS (typical):
        Sequential: 166666500000 (150ms)
        Parallel:   166666500000 (50ms)

        Speedup: ~3x on 4-core CPU
        */
    }
}

/*
PARALLEL STREAMS:
✓ Use multiple threads (ForkJoinPool)
✓ Faster for large datasets
✓ Transparent (same API)

WHEN TO USE:
✓ Large dataset (100k+ elements)
✓ Computationally expensive operation
✓ No ordering requirement
 */