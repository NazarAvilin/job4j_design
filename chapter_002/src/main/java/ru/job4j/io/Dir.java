package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        System.out.printf("size : %s%n", file.getTotalSpace());
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("name:%s size:%d KB%n",
                    subFile.getName(), (long) Math.ceil(subFile.length() / 1024.0));
        }

    }
}