package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analyses {

    private static final String NORMAL_STATUS = "[123].+";

    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            boolean flag = false;
            String start = "";
            String line;
            do {
                line = br.readLine();
                if (line != null) {
                    boolean isNormal = line.matches(NORMAL_STATUS);
                    if (!flag && !isNormal && line.startsWith("500")) {
                        start = line.split(" ")[1];
                        flag = true;
                    } else if (!flag && !isNormal && line.startsWith("400")) {
                        start = line.split(" ")[1];
                        flag = true;
                    } else if (flag && isNormal) {
                        bw.write(String.format("%s;%s%n", start, line.split(" ")[1]));
                        flag = false;
                    }
                }
            } while (line != null);
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
        Analyses analyses = new Analyses();
        analyses.unavailable("server.txt", "unavailable.csv");
    }
}
