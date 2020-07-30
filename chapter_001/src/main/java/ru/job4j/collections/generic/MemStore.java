package ru.job4j.collections.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();


    public int position(final String id) {
        int index = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }



    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = position(id) != -1;
        if (rsl) {
            mem.set(position(id), model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = position(id) != -1;
        if (rsl) {
            mem.remove(position(id));
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        if (position(id) != -1) {
            rsl = mem.get(position(id));
        }
        return rsl;
    }
}