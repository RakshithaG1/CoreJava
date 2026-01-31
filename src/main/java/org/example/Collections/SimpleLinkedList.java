package org.example.Collections;

public class SimpleLinkedList<T> {

    private class Node<T> {
        T data;
        Node next;
        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void addLast(T element) {
        Node newNode = new Node(element);

        if(tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void  addFirst(T element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        if(tail == null) {
            tail = newNode;
        }
        size++;
    }

//    public T get(int index) {
//        Node current = head;
//        //while (i =0 ;i<index;i++)
//    }
}
