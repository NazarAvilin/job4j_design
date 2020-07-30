package ru.job4j.collections.map;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class SimpleHashMapTest {

    @Test
    public void whenAddPairThenGet() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(1, 1);
        map.insert(2, 2);
        map.insert(3, 3);
        assertThat(map.get(2), is(2));
        assertThat(map.get(1), is(1));
        assertThat(map.get(3), is(3));
    }

    @Test
    public void whenAddPairWithSameKeyThenGet() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(1, 1);
        map.insert(2, 2);
        map.insert(3, 3);
        map.insert(1, 10);
        map.insert(2, 20);
        map.insert(3, 30);
        assertThat(map.get(1), is(10));
        assertThat(map.get(2), is(20));
        assertThat(map.get(3), is(30));
    }

    @Test
    public void whenAddAndInvokeNextThenGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndDeleteThenGetByNext() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(1, 1);
        map.insert(2, 2);
        map.insert(3, 3);
        map.insert(1, 10);
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCreateIterAndAddThenThrowCME() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        Iterator<Integer> it = map.iterator();
        map.insert("second", 2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyIterThenThrowNSEE() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        Iterator<Integer> it = map.iterator();
        it.next();
    }

    @Test
    public void whenInsertDeleteThenGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 3);
        map.delete("first");
        assertNull(map.get("first"));
        assertThat(map.get("second"), is(2));
        assertThat(map.get("third"), is(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenNullKey() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert(null, 1);
        Iterator<Integer> it = map.iterator();
        it.next();
    }

}