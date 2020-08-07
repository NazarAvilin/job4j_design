package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        for (var source : sources) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Zip().packSingleFile(
                new File("./chapter_002/pom.xml"),
                new File("./chapter_002/pom.zip")
        );

        ArgZip argZip = new ArgZip(args);
        String exclude = argZip.exclude();
        String directory = argZip.directory();
        String output = argZip.output();
        Path root = Paths.get(directory);
        List<Path> zipFile = new ArrayList<>();
        try {
            zipFile = Search.search(root, exclude);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<File> files = new ArrayList<>();
        for (Path path : zipFile) {
            files.add(path.toFile());
        }
        new Zip().packFiles(files, new File(output));
    }
}
