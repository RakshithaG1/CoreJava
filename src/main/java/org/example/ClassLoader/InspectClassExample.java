package org.example.ClassLoader;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class InspectClassExample {

    public static void main(String[] args) {
        Class<?> personClass = Person.class;

        // 1. Class Name
        System.out.println("Name: " + personClass.getName());
        System.out.println("Simple Name: " + personClass.getSimpleName());
        System.out.println("Canonical Name: " + personClass.getCanonicalName());

        // 2. Superclass
        System.out.println("Superclass: " + personClass.getSuperclass());

        // 3. Interfaces
        Class<?>[] interfaces = personClass.getInterfaces();
        System.out.println("Interfaces: " + Arrays.toString(interfaces));

        // 4. Modifiers
        int modifiers = personClass.getModifiers();
        System.out.println("Is Public: " + Modifier.isPublic(modifiers));
        System.out.println("Is Abstract: " + Modifier.isAbstract(modifiers));
        System.out.println("Is Final: " + Modifier.isFinal(modifiers));

        // 5. Type check
        System.out.println("Is Interface: " + personClass.isInterface());
        System.out.println("Is Enum: " + personClass.isEnum());
        System.out.println("Is Array: " + personClass.isArray());
        System.out.println("Is Primitive: " + personClass.isPrimitive());

        // 6. Package
        System.out.println("Package: " + personClass.getPackage());
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}