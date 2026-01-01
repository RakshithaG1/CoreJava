package org.example.ClassLoader;
// Part 1A: ClassLoader FundamentalsClassLoader Hierarchy
// ClassLoader is responsible for loading classes at runtime
public class ClassLoaderExample {

    public static void main(String[] args) {
        Class<?> stringClass = String.class;

        // Get ClassLoader for String
        ClassLoader loader = stringClass.getClassLoader();
        System.out.println("ClassLoader: " + loader);
        // Output: null (Bootstrap ClassLoader - implemented in C/C++)

        // Get ClassLoader for custom class
        ClassLoader myLoader = ClassLoaderExample.class.getClassLoader();
        System.out.println("ClassLoader: " + myLoader);
        // Output: sun.misc.Launcher$AppClassLoader@... (Application ClassLoader)
    }
}