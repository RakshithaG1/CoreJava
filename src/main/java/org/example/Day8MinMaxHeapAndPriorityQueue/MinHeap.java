package org.example.Day8MinMaxHeapAndPriorityQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;
//Start to write Min Heap class

public class MinHeap {
    private int[] heap;
    private int size;
    MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    private int getParent(int index) {
        return (index-1)/2;
    }

    private int getLeftChild(int index) {
        return index*2+1;
    }

    private int getRightChild(int index) {
        return index*2+2;
    }

    public void insert(int val) {
        if(size == heap.length) {
            resize();
        }
        heap[size] = val;
        bubbleUp(size);
        size++;
    }

    private void resize() {
        int newLength = heap.length * 2;
        int[] newHeap = new int[newLength];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }


    private void bubbleUp(int index) {
        if (index == 0) {
            return;
        }

        int parent = getParent(index);
        if(heap[index] < heap[parent]) {
            swap(index, parent);
            bubbleUp(parent);
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public int extractMin() {
        if(size == 0) {
                throw new NoSuchElementException("Heap is empty");
            };

        int min = heap[0];
        heap[0] = heap[size-1];
        size--;
        bubbleDown(0);
        return min;
    }

    private void bubbleDown(int index) {
        int smallest = index;
        int left = getLeftChild(index);
        int right = getRightChild(index);
        if(left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest != index) {
            swap(smallest, index);
            bubbleDown(smallest);
        }
    }

    // Peek: O(1)
    public int peek() {
        if (size == 0) throw new NoSuchElementException();
        return heap[0];
    }

    public int getSize() {
        return size;
    }

        public static void main(String[] args) {
            MinHeap heap = new MinHeap(10);

            heap.insert(5);
            heap.insert(3);
            heap.insert(7);
            heap.insert(1);
            heap.insert(4);

            System.out.println(heap.peek());  // 1
            System.out.println(Arrays.toString(heap.heap));  // 1

            while (heap.getSize() > 0) {
                System.out.print(heap.extractMin() + " ");
            }
            // Output: 1 3 4 5 7
        }

}
