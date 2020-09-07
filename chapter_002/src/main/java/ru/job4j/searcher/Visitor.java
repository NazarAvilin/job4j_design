package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;

public class Visitor implements FileVisitor<Path> {
    private final List<Path> list = new ArrayList<>();
    private final Predicate<Path> condition;

    public Visitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            list.add(file);
        }
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
