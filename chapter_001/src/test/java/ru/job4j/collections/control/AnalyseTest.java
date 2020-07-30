package ru.job4j.collections.control;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class AnalyseTest {
    @Test
    public void when1Added1Changed1Deleted2Same() {
        List<Analyse.User> previous = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D")
        );
        List<Analyse.User> current = List.of(
                new Analyse.User(1, "AA"),
                new Analyse.User(11, "S"),
                new Analyse.User(2, "B"),
                new Analyse.User(4, "D")
        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenAllNewAddedThenAddedEqualToDeleted() {
        List<Analyse.User> previous = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D")
        );
        List<Analyse.User> current = List.of(
                new Analyse.User(5, "E"),
                new Analyse.User(6, "F"),
                new Analyse.User(7, "G"),
                new Analyse.User(8, "H")
        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(4));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(4));
    }

    @Test
    public void whenReversedOrderThenNoChanges() {
        List<Analyse.User> previous = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D")
        );
        List<Analyse.User> current = List.of(
                new Analyse.User(4, "D"),
                new Analyse.User(3, "C"),
                new Analyse.User(2, "B"),
                new Analyse.User(1, "A")
        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAllAreChangedThenChanged4() {
        List<Analyse.User> previous = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D")
        );
        List<Analyse.User> current = List.of(
                new Analyse.User(1, "W"),
                new Analyse.User(2, "X"),
                new Analyse.User(3, "Y"),
                new Analyse.User(4, "Z")
        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(4));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAllAreDeleted() {
        List<Analyse.User> previous = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D")
        );
        List<Analyse.User> current = List.of(
        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(4));
    }

    @Test
    public void whenAllAreAdded() {
        List<Analyse.User> previous = List.of(
        );
        List<Analyse.User> current = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D")
        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(4));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void when4Added1Changed1Deleted() {
        List<Analyse.User> previous = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D")
        );
        List<Analyse.User> current = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(4, "@"),
                new Analyse.User(5, "E"),
                new Analyse.User(6, "F"),
                new Analyse.User(7, "G"),
                new Analyse.User(8, "H")

        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(4));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void when2Added2Changed4Deleted() {
        List<Analyse.User> previous = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(3, "C"),
                new Analyse.User(4, "D"),
                new Analyse.User(5, "E"),
                new Analyse.User(6, "F"),
                new Analyse.User(7, "G"),
                new Analyse.User(8, "H"),
                new Analyse.User(9, "I"),
                new Analyse.User(10, "J")
        );
        List<Analyse.User> current = List.of(
                new Analyse.User(1, "A"),
                new Analyse.User(2, "B"),
                new Analyse.User(11, "K"),
                new Analyse.User(5, "EE"),
                new Analyse.User(12, "L"),
                new Analyse.User(7, "G"),
                new Analyse.User(8, "HH"),
                new Analyse.User(10, "J")
        );
        Analyse.Info info = new Analyse().diff(previous, current);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(4));
    }
}