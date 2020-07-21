package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {

    private final int[] data;
    private int point;

    public EvenIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                point = i;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
