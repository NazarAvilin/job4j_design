package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int cursor = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public int position(int index) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException("Array is full!");
        }
        return Objects.checkIndex(index, cursor);
    }

    public void add(final T model) {
        array[position(cursor++)] = model;
    }

    public void set(int index, T model) {
        array[position(index)] = model;
    }

    public void remove(int index) {
        position(index);
        array[index] = null;
        System.arraycopy(array, index + 1, array,
                index, cursor - 1 - index);
        cursor--;
        array[cursor] = null;
    }

    public T get(int index) {
        return (T) array[position(index)];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int position = 0;

            @Override
            public boolean hasNext() {
                return position < cursor;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[position++];
            }
        };
    }
}
