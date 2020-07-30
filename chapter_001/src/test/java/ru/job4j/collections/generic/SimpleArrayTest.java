package ru.job4j.collections.generic;

import org.junit.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class SimpleArrayTest {
    @Test
    public void whenArrayTypeIntegerAddSameElementsThenGetIntegerByIndex() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        assertThat(simpleArray.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArrayTypeIntegerAddMoreElementsThenException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
    }

    @Test
    public void whenAddTwoAndRemoveOneThenNewAddInEnd() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.remove(0);
        simpleArray.add(11);
        assertThat(simpleArray.get(0), is(2));
        assertThat(simpleArray.get(1), is(11));
    }

    @Test
    public void whenArrayTypeStringAndEditElementThenEdit() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("test1");
        simpleArray.add("TEST2");
        simpleArray.set(1, "test2");
        assertThat(simpleArray.get(1), is("test2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArrayTypeStringAndMoreElementsThenException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("test1");
        simpleArray.add("TEST2");
        simpleArray.set(2, "test2");
    }

    @Test
    public void whenArrayTypeListOfBooleanAndRemoveElementThenRemove() {
        SimpleArray<List<Boolean>> simpleArray = new SimpleArray<>(4);
        simpleArray.add(List.of(true, true, false, false));
        simpleArray.add(List.of(true, false, false));
        simpleArray.add(List.of(false, false));
        simpleArray.add(List.of(false));
        simpleArray.remove(1);
        assertThat(simpleArray.get(0).size(), is(4));
        assertThat(simpleArray.get(1).size(), is(2));
        assertThat(simpleArray.get(2).size(), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArrayTypeListOfBooleanAndMoreElementsThenException() {
        SimpleArray<List<Boolean>> simpleArray = new SimpleArray<>(2);
        simpleArray.add(List.of(true, true, false, false));
        simpleArray.add(List.of(true, false, false));
        simpleArray.remove(3);
    }

    @Test
    public void whenAddSameIntegerThanGetIterator() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(7);
        for (int i = 1; i <= 7; i++) {
            simpleArray.add(i);
        }
        Iterator<Integer> result = simpleArray.iterator();
        for (Integer integer : Arrays.asList(1, 2, 3, 4, 5, 6, 7)) {
            assertThat(result.next(), is(integer));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNoSuchElementExceptionThenNoMoreNoNullElements() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        Iterator<Integer> result = simpleArray.iterator();
        result.next();
        result.next();
        result.next();
    }
}