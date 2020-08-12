package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;

public class FileVisitor {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        Files.walkFileTree(start, new FileGetter());
        FileGetter fileGetter = new FileGetter();
        fileGetter.getDuplicates();
    }
}
