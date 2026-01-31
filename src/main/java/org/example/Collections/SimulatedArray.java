package org.example.Collections;

//Backed by array
//Randomized access--> o(1)
//Insertion/Deletion --> 0(n)-->due to shifting
//Best for read heavy operation
public class SimulatedArray<T> {
    private T[] data;
    private int size;

    public SimulatedArray() {
        data = (T[]) new Object[10];
        size = 0;
    }

    public void add(T element) {
        if(size == data.length) {
            expand();
        }
        data[size++] =element;
    }

    public void expand() {
        T[] newData = (T[]) new Object[2* data.length];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public T get(int index) {
        return data[index];
    }

    public void add(T element, int index) {
        if(size == data.length) {
            expand();
        }
        System.arraycopy(data, index, data, index+1, size-index);
        data[index] = element;
        size++;
    }
}
