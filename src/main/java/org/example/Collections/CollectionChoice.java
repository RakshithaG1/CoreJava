package org.example.Collections;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class CollectionChoice {

    public static void main(String[] args) {

        // ✅ Use ArrayList when:
        // - Frequent random access (get by index)
        // - Mostly appending to end
        // - Need memory efficiency
        ArrayList<String> words = new ArrayList<>();
        words.add("hello");
        String first = words.get(0);  // Fast random access

        // ✅ Use LinkedList when:
        // - Frequent insertion/deletion at ends
        // - Using as Queue or Deque
        // - Don't need random access
        LinkedList<String> queue = new LinkedList<>();
        queue.addFirst("first");
        queue.removeLast();  // O(1) operations

        // ✅ Use Deque interface (ArrayList or LinkedList)
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.removeFirst();
        deque.removeLast();

        // ✅ Use Vector (legacy) - Synchronized, slower
        // Vector<String> legacy = new Vector<>();  // Avoid in modern code

        // ✅ Use CopyOnWriteArrayList for thread-safe reads
        // CopyOnWriteArrayList<String> concurrent = new CopyOnWriteArrayList<>();
    }
}