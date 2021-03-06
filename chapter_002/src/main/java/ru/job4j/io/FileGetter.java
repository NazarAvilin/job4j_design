package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FileGetter implements FileVisitor<Path> {
    private final Set<Path> duplicates = new HashSet<>();
    private final List<Path> metFile = new ArrayList<>();

    public Set<Path> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Path source = file.getFileName();
        if (metFile.contains(source)) {
            duplicates.add(file);
        } else {
            metFile.add(source);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }
}