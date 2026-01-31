package org.example.Day7FlatMap;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
///*
//OPTIONAL FLATMAP:
//- Optional.stream() returns Stream (1 element if present, 0 if empty)
//- Allows Optional to work in stream pipelines
//- flatMap handles absence gracefully
//*/
public class OptionalFlatMap {

    static class User {
        String name;
        Optional<String> email;  // May or may not have email

        User(String name, Optional<String> email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Alice", Optional.of("alice@example.com")),
                new User("Bob", Optional.empty()),
                new User("Charlie", Optional.of("charlie@example.com"))
        );

        // ✅ CORRECT: Optional.flatMap flattens
        List<String> emails = users.stream()
                .flatMap(user -> user.email.stream())
                .collect(Collectors.toUnmodifiableList());


    }
}