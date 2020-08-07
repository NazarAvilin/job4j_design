package ru.job4j.io;

import java.util.Arrays;

public class ArgZip {

    private final String[] args;
    private final int arguments = 3;
    private final int keys = 3;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != arguments) {
            throw new IllegalArgumentException("Not enough arguments!");
        }
        long counter = Arrays.stream(args).filter(k -> k.startsWith("-")).count();
        if (counter != keys) {
            throw new IllegalArgumentException("Not enough keys!");
        }
        return true;
    }

    public String findByKey(String key) {
        String rsl = "";
        if (!valid()) {
            throw new IllegalArgumentException("There is not enough arguments or keys");
        }
        for (var arg : args) {
            if (key != null && arg.startsWith(key)) {
                rsl = arg.split("=")[1];
            }
        }
        return rsl;
    }

    public String directory() {
        return findByKey("-d");
    }

    public String exclude() {
        return findByKey("-d");
    }

    public String output() {
        return findByKey("-o");
    }

}
