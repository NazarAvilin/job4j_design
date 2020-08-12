package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;

public class FileVisitor {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(args[0]);
        FileGetter fileGetter = new FileGetter();
        Files.walkFileTree(start, fileGetter);
        fileGetter.getDuplicates().forEach(p ->
                System.out.println(p.toAbsolutePath().toString()));
    }
}
