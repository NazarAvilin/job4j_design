package ru.job4j.io;

import java.io.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.nio.file.Files;
import org.junit.rules.TemporaryFolder;
import static org.hamcrest.core.Is.is;


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

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    @Test
    public void whenReadFromTemporaryFile() throws IOException {
        Analyses an = new Analyses();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Files.write(source.toPath(), List.of("200 10:56:01",
                                            "500 10:57:01",
                                            "400 10:58:01",
                                            "200 10:59:01",
                                            "500 11:01:02",
                                            "200 11:02:02"));
        an.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expect = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
        List<String> result = Files.readAllLines(target.toPath());
//        StringJoiner rsl = new StringJoiner(" ");
//        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
//            in.lines().forEach(rsl::add);
//        }
        assertThat(result, is(expect));
    }

}