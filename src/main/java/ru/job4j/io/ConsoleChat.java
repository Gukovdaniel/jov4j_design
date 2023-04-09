package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private boolean isBotMuted;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        ArrayList<String> strings = new ArrayList<>();
        System.out.print("Вы: ");
        String input = scanner.nextLine();
        strings.add("Вы: " + input);

        if (STOP.equalsIgnoreCase(input)) {
            isBotMuted = true;
        } else if (CONTINUE.equalsIgnoreCase(input)) {
            isBotMuted = false;
        } else if (OUT.equalsIgnoreCase(input)) {
            return;
        } else if (!isBotMuted) {
            String response = readPhrases().get(random.nextInt(readPhrases().size()));
            System.out.println("Бот: " + response);
            strings.add("Бот: " + response);
            saveLog(strings);
        }
        run();
    }

        private List<String> readPhrases() {
        ArrayList<String> responses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responses.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responses;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(botAnswers, true))) {
            for (String s : log) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/respounces.txt", "data/dialog.txt");
        cc.run();
    }
}
