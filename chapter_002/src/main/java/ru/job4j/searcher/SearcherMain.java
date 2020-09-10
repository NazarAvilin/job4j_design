package ru.job4j.searcher;

import java.io.*;
import java.nio.file.Path;
import java.util.List;


public class SearcherMain {
    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                    "Введите необходимые ключи:" + System.lineSeparator()
                            + "-d   директория, в которой начинать поиск" + System.lineSeparator()
                            + "-n   имя файла, маска, либо регулярное выражение" + System.lineSeparator()
                            + "-m   искать по маске файла (-f полному совпадению,"
                            + " -r регулярному выражению)" + System.lineSeparator()
                            + "-o   путь, по которому записать в файл");
        }
        new SearcherMain().run(args);
    }

    public void run(String[] args) {
        Searcher searcher = new Searcher();
        List<Path> wantedFiles = null;

        try {
            wantedFiles = searcher.search(args[0], args[1], args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Writer saver = new Writer();

        String target = args[3].replace("-o ", "");

        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            assert wantedFiles != null;
            saver.writeToFile(wantedFiles, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}