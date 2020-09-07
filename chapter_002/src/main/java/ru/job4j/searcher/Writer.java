package ru.job4j.searcher;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class Writer {

    public void writeToFile(List<Path> list, java.io.Writer writer) throws IOException {
        for (Path file : list) {
            writer.write(file.toString() + System.lineSeparator());
        }
    }
}
