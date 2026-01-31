package org.example.ClassLoader;

//Getting Class Objects// Four ways to get Class object
public class GetClassExample {

    public static void main(String[] args) throws ClassNotFoundException {

        // Method 1: Using .class
        System.out.println("Using FullyQualied Class Name with .class");
        Class<?> class1 = org.example.ClassLoader.AnotherClass.class;

        // Method 2: Using Class.forName()
        System.out.println("Using Class.forName");
        Class<?> class2 = Class.forName("org.example.ClassLoader.AnotherClass");

        // Method 3: Using getClass() on object
        System.out.println("Using Shallow object");
        AnotherClass str = new AnotherClass();
        Class<?> class3 = str.getClass();

        // Method 4: Using ClassLoader.loadClass()
        System.out.println("Using ClassLoader.loadClass()");
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> class4 = loader.loadClass("org.example.ClassLoader.AnotherClass");

        // All are equal
        System.out.println(class1 == class2);  // true
        System.out.println(class2 == class3);  // true
        System.out.println(class3 == class4);  // true
    }
}
