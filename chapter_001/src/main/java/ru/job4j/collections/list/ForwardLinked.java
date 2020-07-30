package ru.job4j.collections.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        node.next = head;
        head = node;
        size++;
    }

    public T deleteFirst() {
        Node<T> nodeFirst;
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            nodeFirst = head;
            head = nodeFirst.next;
            size--;
        }
        return nodeFirst.getValue();
    }

    public void revert() {
        Node<T> reversed = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = reversed;
            reversed = current;
            current = next;
        }
        head = reversed;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }
    }
}