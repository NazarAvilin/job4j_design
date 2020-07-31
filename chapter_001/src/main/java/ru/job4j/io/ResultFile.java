package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i <= 10; i++) {
                out.write(System.lineSeparator().getBytes());
                for (int j = 1; j <= 10; j++) {
                    out.write((i + " * " + j + " = " + i * j).getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}