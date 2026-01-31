package org.example.Day8MinMaxHeapAndPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueEx {
    public static void main(String[] args) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Comparator.reverseOrder());
        pQueue.add(8);
        pQueue.add(1);
        pQueue.add(2);
        pQueue.add(3);
        pQueue.add(4);

        System.out.println("Priority Queue=="+pQueue);



    }
}
