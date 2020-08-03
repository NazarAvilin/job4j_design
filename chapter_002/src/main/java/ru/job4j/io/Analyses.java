package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analyses {

    public void unavailable(String source, String target) {
        StringJoiner rsl = new StringJoiner("");
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(
                     new FileOutputStream(target)))) {
            in.lines().forEach(line -> {
                char lineEnd = '\u0000';
                if (rsl.length() > 0) {
                    lineEnd = rsl.toString().charAt(rsl.toString().length() - 1);
                }
                if ((line.startsWith("400") || line.startsWith("500")) && (lineEnd != ';')) {
                    rsl.add(line.substring(4) + ";");
                } else if ((line.startsWith("200") || line.startsWith("300"))
                        && (lineEnd == ';')) {
                    rsl.add(line.substring(4) + System.lineSeparator());
                }
            });
            out.write(rsl.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readResult(String path) {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
