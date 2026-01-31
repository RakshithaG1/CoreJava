package org.example.Day7FlatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//map(person → person.hobbies): Stream<List<String>>
//flatMap(person → person.hobbies.stream()): Stream<String>
//
//flatMap combines mapping + flattening!
public class MapVsFlatMap {
    static class Person {
        String name;
        List<String> hobbies;

        Person(String name, List<String> hobbies) {
            this.name = name;
            this.hobbies = hobbies;
        }
    }
    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Alice", Arrays.asList("reading", "gaming")),
                new Person("Bob", Arrays.asList("sports", "cooking")),
                new Person("Charlie", Arrays.asList("music"))
        );

        // ❌ WRONG: map() creates nested stream
        List<List<String>> nestedHobbies = people.stream()
                .map(person -> person.hobbies)  // Stream<List<String>>
                .collect(Collectors.toList());

        System.out.println("Nested: " + nestedHobbies);
        // [[reading, gaming], [sports, cooking], [music]]
        // Difficult to use

        // ✅ CORRECT: flatMap() flattens
        List<String> allHobbies = people.stream()
                .flatMap(person -> person.hobbies.stream())  // Flatten
                .collect(Collectors.toList());

        System.out.println("Flattened: " + allHobbies);
        // [reading, gaming, sports, cooking, music]

        // Count total hobbies
        long count = people.stream()
                .flatMap(person -> person.hobbies.stream())
                .count();

        System.out.println("Total hobbies: " + count);  // 5

        // Find people with specific hobby
        List<String> withMusic = people.stream()
                .filter(p -> p.hobbies.contains("music"))
                .map(p -> p.name)
                .collect(Collectors.toList());

        System.out.println("With music: " + withMusic);
    }
}