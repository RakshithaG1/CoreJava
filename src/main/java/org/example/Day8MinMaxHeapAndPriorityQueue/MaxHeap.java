package org.example.Day8MinMaxHeapAndPriorityQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;



//A Max Heap is dataStructure where parent is greater than 2 of its childrens
public class MaxHeap {
    private int [] heap;
    //size is pointer to insert and keep track of current heap size
    private int size;
    MaxHeap(int capacity) {
        heap = new int[capacity];
        size=0;
    }

    private int getParent(int index) {
        return (index -1)/2;
    }

    private int getLeftChild(int index) {
        return index*2+1;
    }

    private int getRightChild(int index) {
        return index*2+2;
    }

    private void swap(int i, int j) {
        int temp =  heap[j];
        heap[j] = heap[i];
        heap[i] = temp;
    }

    public void insert(int val) {
        if(size == heap.length) {
            resize();
        }
        heap[size] = val;
        bubbleUp(size);
        size++;
    }

    private void bubbleUp(int index) {
        if (index == 0) {
            return;
        }
        int parentIndex = getParent(index);
        if(heap[index] > heap[parentIndex]) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    private void resize() {
        int newLength = 2*heap.length;
        int [] newHeap = new  int[newLength];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    public int extractMax() {
        if(size == 0) {
            throw new NoSuchElementException();
        }

        int max = heap[0];
        heap[0] = heap[size-1];
        size--;
        bubbleDown(0);
        return max;
    }

    private void bubbleDown(int index) {

        int largest = index;
        int left = getLeftChild(index);
        int right = getRightChild(index);

        if(left < size && heap[left] > heap[largest]) {
            largest =  left;
        }
        if(right < size && heap[right] > heap[largest]) {
            largest =  right;
        }
        if(largest != index) {
            swap(index, largest);
            bubbleDown(largest);
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(3);
        maxHeap.insert(5);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(2);
        maxHeap.insert(7);
        System.out.println(Arrays.toString(maxHeap.heap));


    }

}