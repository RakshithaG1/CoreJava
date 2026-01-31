package org.example.ClassLoader;

public class AnotherClass {
    final static int id = 10;
    AnotherClass() {
        System.out.println("Default Constructor");
    }
    static {
        System.out.println("executing this block for forName()");
    }

}
