package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleLinkedList<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    public void add(final E value) {
        Node<E> node = last;
        Node<E> newNode = new Node<>(node, value, null);
        last =  newNode;
        if (node == null) {
            first = newNode;
        } else {
            node.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(final int index) {
        Objects.checkIndex(index, size);
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int pointer;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(pointer);
            }
        };
    }

    public static class Node<E> {
        private final E item;
        private Node<E> next;
        private final Node<E> prev;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}