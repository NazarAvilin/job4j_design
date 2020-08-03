package ru.job4j.io;

import org.junit.Test;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalysesTest {

    @Test
    public void whenFindUnavailableDiapasons() {
        String serverLog = "./data/server.txt";
        Analyses anls = new Analyses();
        anls.unavailable(serverLog, "./data/unavailable.csv");
        String result = anls.readResult("./data/unavailable.csv");
        assertThat(result, is(new StringJoiner(System.lineSeparator())
                .add("10:57:01;10:59:01")
                .add("11:01:02;11:02:02").toString()));
    }

}