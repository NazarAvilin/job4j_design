package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ConsoleChat {
    private final String inPath;
    private final String outPath;
    private List<String> input = new ArrayList<>();
    private final List<String> output = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleChat(String inPath, String outPath) {
        this.inPath = inPath;
        this.outPath = outPath;
    }

    public void read() throws IOException {
        input = Files.readAllLines(Path.of(inPath));
    }

    public void write() throws IOException {
        Files.write(Path.of(outPath), output);
    }

    public String answers() {
        int i = (int) (Math.random() * input.size());
        return input.get(i);
    }

    public void begin() throws IOException {
        read();
        boolean go;
        boolean botAnswer = true;
        do {
            String bot = answers();
            System.out.print("Ты: ");
            String you = scanner.nextLine();
            go = !you.equals("закончить");
            if (go) {
                if (you.equals("продолжить")) {
                    botAnswer = true;
                }
                if (you.equals("стоп")) {
                    botAnswer = false;
                }
                output.add("Ты: " + you);
                if (botAnswer) {
                    System.out.println("Бот: " + bot);
                    output.add("Бот: " + bot);
                }
            }
        } while (go);
        write();
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./chapter_002/data/input.txt", "./chapter_002/data/output.txt");
        cc.begin();
    }
}