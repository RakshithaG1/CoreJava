package org.example.ClassLoader;

public class DelegationExample {
    public static void main(String[] args) throws ClassNotFoundException {
        // Load String class
        // 1. AppClassLoader checks - not found
        // 2. ExtClassLoader checks - not found
        // 3. BootstrapClassLoader loads from rt.jar
        Class<?> stringClass = Class.forName("java.lang.String");
        System.out.println(stringClass.getClassLoader());  // null

        // Load MyClass
        // 1. AppClassLoader checks CLASSPATH - FOUND!
        Class<?> myClass = Class.forName("org.example.ClassLoader.ClassLoaderExample");
        System.out.println(myClass.getClassLoader());  // AppClassLoader
    }
}
