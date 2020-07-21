package ru.job4j.set;

import ru.job4j.list.SimpleArray;
import java.util.Iterator;
import java.util.Objects;


public class SimpleSet<E> implements Iterable<E> {

    SimpleArray<E> set = new SimpleArray<>();

    public boolean contains(E value) {
        boolean rsl = false;
        for (var el : set) {
            if (Objects.equals(el, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public void add(E e) {
        if (!contains(e)) {
           set.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
