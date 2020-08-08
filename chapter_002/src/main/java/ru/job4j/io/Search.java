package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null or file type in not mentioned");
        }
        Set<String> list = new HashSet<>();
        Path start = Paths.get(args[0]);
        search(start, args[1]).forEach(line -> list.add(line + System.lineSeparator()));
        System.out.println(list);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
