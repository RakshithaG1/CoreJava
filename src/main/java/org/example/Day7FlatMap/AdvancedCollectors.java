package org.example.Day7FlatMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AdvancedCollectors {

    static class Product {
        String name;
        String category;
        double price;

        Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200),
                new Product("Mouse", "Electronics", 25),
                new Product("Book", "Books", 15),
                new Product("Pen", "Supplies", 5),
                new Product("Monitor", "Electronics", 400)
        );

        // 1. GROUP BY CATEGORY
        Map<String, List<Product>> byCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("By category: " + byCategory);
        // {Electronics=[...], Books=[...], Supplies=[...]}

        // 2. GROUP BY + MAPPING
        Map<String, List<String>> namesByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.mapping(p -> p.name, Collectors.toList())));

        System.out.println("Names by category: " + namesByCategory);
        // {Electronics=[Laptop, Mouse, Monitor], Books=[Book], ...}

        // 3. GROUP BY + COUNTING
        Map<String, Long> countByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.counting()));

        System.out.println("Count by category: " + countByCategory);
        // {Electronics=3, Books=1, Supplies=1}

        // 4. GROUP BY + SUMMING
        Map<String, Double> priceByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.summingDouble(p -> p.price)));
        System.out.println("Price by category: " + priceByCategory);
        // {Electronics=1625.0, Books=15.0, Supplies=5.0}

        // 5. JOIN STRINGS
        String productList = products.stream()
                .map(p -> p.name)
                .collect(Collectors.joining(", "));

        System.out.println("Products: " + productList);
        // Laptop, Mouse, Book, Pen, Monitor

        // 6. PARTITION BY PREDICATE
        Map<Boolean, List<Product>> expensive = products.stream()
                .collect(Collectors.partitioningBy(p->p.price > 100));
        System.out.println("Expensive: " + expensive.get(true));
        System.out.println("Cheap: " + expensive.get(false));
    }
}
//COMMON COLLECTORS:
//- toList(): Collect to List
//- toSet(): Collect to Set
//- groupingBy(): Group by condition
//- partitioningBy(): Split by boolean
//- joining(): Join strings
//- mapping(): Transform before collecting
//- counting(): Count elements
//- summing*(): Sum numeric values
//- averaging*(): Calculate average
//- reducing(): Custom reduction
//*/