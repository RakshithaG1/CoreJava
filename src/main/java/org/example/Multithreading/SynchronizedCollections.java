package org.example.Multithreading;

//Concurrent collections
//ways to make collections thread safe
//use collections.synchronize() method
//use the concurrent collections which are synchrnoized

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Downside of using the collections.synchronized() approach
//coarse grained locking
//Limited functionality
//No fail Fast Iterators
//Performance Overhead
public class SynchronizedCollections {
    public static void main(String args[]) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        Thread one = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(list.size());
    }
}