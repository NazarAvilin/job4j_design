package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();
            in.lines().forEach(line -> {
                if (line.contains("404")) {
                    lines.add(line + System.lineSeparator());
                }
            });
            rsl.addAll(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}