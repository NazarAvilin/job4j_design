package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void multiplyTable(int step) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i <= step; i++) {
                for (int j = 1; j <= step; j++) {
                    out.write((i + " x " + j + " = " + (i * j) + System.lineSeparator()).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiplyTable(12);
    }
}