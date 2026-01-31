package org.example.Day7FlatMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPerformance {

    static class Item {
        int id;
        String category;
        double price;

        Item(int id, String category, double price) {
            this.id = id;
            this.category = category;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            items.add(new Item(i, "cat" + (i % 10), Math.random() * 100));
        }

        // ❌ INEFFICIENT: Collecting intermediate results
        List<Item> filtered1 = items.stream()
                .filter(item -> item.price > 50)
                .collect(Collectors.toList());

        List<Item> filtered2 = filtered1.stream()
                .filter(item -> item.category.equals("cat5"))
                .collect(Collectors.toList());

        // ✅ EFFICIENT: Single pipeline
        List<Item> filtered = items.stream()
                .filter(item -> item.price > 50)
                .filter(item -> item.category.equals("cat5"))
                .collect(Collectors.toList());

        // ❌ INEFFICIENT: Multiple passes
        long count = items.stream()
                .filter(item -> item.price > 50)
                .collect(Collectors.toList())
                .stream()
                .count();

        // ✅ EFFICIENT: Single pass
        long count2 = items.stream()
                .filter(item -> item.price > 50)
                .count();

        // ✅ USE PEEK FOR DEBUGGING
        items.stream()
                .filter(item -> item.price > 50)
                .peek(item -> System.out.println("After filter: " + item.id))
                .limit(5)
                .forEach(item -> System.out.println("Final: " + item.id));
    }
}

/*
PERFORMANCE BEST PRACTICES:
✓ Single pipeline (no intermediate collect)
✓ Filter early (reduce data size)
✓ Use appropriate collector
✓ Use parallel for large datasets
✓ Avoid side effects (use peek for debug only)
✓ Stateless operations preferred
*/
//Interview Q&A: StreamsQ1: What's the difference between map and flatMap?
// A: map() transforms each element.
// flatMap() transforms each element to stream and flattens result into single stream.
// Q2: When should you use parallel streams?
// A: For large datasets (100k+) with expensive operations.
// Overhead outweighs benefits for small data.
// Q3: What are intermediate vs terminal operations?
// A:
//Intermediate: filter, map, flatMap (lazy, don't execute)
//Terminal: collect, count, forEach (trigger execution)
