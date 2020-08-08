package ru.job4j.io;

import java.util.*;
import java.io.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines().forEach(line -> {
                if (!line.isEmpty()) {
                    if (line.contains("//")) { //это важно для того чтобы не пропускать комментарии находящихся на одной страке!
                        line = line.split("/")[0];
                    }
                    if (!line.startsWith("#")) {
                        values.put(line.split("=")[0], line.split("=")[1]);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./chapter_002/data/text.txt"));
    }

}
