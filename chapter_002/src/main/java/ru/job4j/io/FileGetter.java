package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FileGetter implements FileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Path source = file.toAbsolutePath();
        Set<Path> duplicates = new HashSet<>();
        List<Path> list = new ArrayList<>();
        List<Path> temp = new ArrayList<>();
        temp.add(source);
        list.add(source.getFileName());
        for (int i = 0; i < list.size(); i++) {
            Path el1 = list.get(i);
            if (el1 == null) {
                continue;
            }
            for (int j = 0; j < list.size(); j++) {
                Path el2 = list.get(j);
                if (el1.equals(el2)) {
                    duplicates.add(temp.get(j));
                }
            }
        }
        System.out.println(duplicates);

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