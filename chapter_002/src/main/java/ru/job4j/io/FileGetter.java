package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import static java.nio.file.FileVisitResult.CONTINUE;

public class FileGetter implements FileVisitor<Path> {
    private final List<String> source = new ArrayList<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        List<Path> metFiles = new ArrayList<>();
        List<Path> duplicates = new LinkedList<>();
        source.add(String.valueOf(file.toAbsolutePath()));
        for (var el:source) {
            metFiles.add(Path.of(el + System.lineSeparator()));
        }
        for (int i = 0; i < metFiles.size(); i++) {
            Path el1 = metFiles.get(i).getFileName();
            if (el1 == null) {
                continue;
            }
            for (int j = 0; j < metFiles.size(); j++) {
                if (i == j) {
                    continue;
                }
                Path el2 = metFiles.get(j).getFileName();
                if (el1.equals(el2)) {
                    duplicates.add(Path.of(source.get(j) + System.lineSeparator()));
                }
            }
        }
        System.out.println("Source \n" + source);
        System.out.println("MetFiles \n" + metFiles);
        System.out.println("Duplicates \n" + duplicates);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }
}