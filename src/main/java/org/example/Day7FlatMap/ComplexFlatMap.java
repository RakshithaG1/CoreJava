package org.example.Day7FlatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComplexFlatMap {

    static class Order {
        int id;
        List<LineItem> items;

        Order(int id, List<LineItem> items) {
            this.id = id;
            this.items = items;
        }
    }

    static class LineItem {
        String productName;
        double price;

        LineItem(String productName, double price) {
            this.productName = productName;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(
                        new LineItem("Laptop", 1200),
                        new LineItem("Mouse", 25)
                )),
                new Order(2, Arrays.asList(
                        new LineItem("Book", 15),
                        new LineItem("Pen", 5),
                        new LineItem("Notebook", 10)
                ))
        );

        // Get all product names
        List<String> allProducts =
                orders.stream()
                .flatMap(order -> order.items.stream())//Stream<String>.flatMap(lineItem -> lineItem.)
                        .map(item -> item.productName)
                        .collect(Collectors.toUnmodifiableList());

        System.out.println("All products: " + allProducts);
        // [Laptop, Mouse, Book, Pen, Notebook]

        // Total revenue
        double totalRevenue = orders.stream()
                .flatMap(order -> order.items.stream())
                .mapToDouble(item -> item.price)
                .sum();

        System.out.println("Total revenue: $" + totalRevenue);  // 1255
    }
}