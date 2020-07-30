package ru.job4j.collections.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container = new Object[0];
    private int cursor;
    private int modCount;

    /**
     * валидация индекса!
     * @param index вводный индекс!
     * @return индекс после валидации!
     */
    public int position(int index) {
        if (index >= container.length) {
            throw new IndexOutOfBoundsException("Array is full!");
        }
        return Objects.checkIndex(index, cursor);
    }

    public T get(int index) {
        return (T) container[position(index)];
    }

    public void add(T model) {
        if (cursor <= container.length) {
            container = Arrays.copyOf(container, cursor + 2);
        }
        container[cursor++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int position;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return position < cursor;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[position++];
            }
        };
    }
}