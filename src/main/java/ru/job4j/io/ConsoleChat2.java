package ru.job4j.io;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat2 {
    private static final String RESPONSES_FILE = "data/respounces.txt";
    private static final String STOP_WORD = "стоп";
    private static final String CONTINUE_WORD = "продолжить";
    private static final String EXIT_WORD = "выход";
    private static final String DIALOG_FILE = "data/dialog.txt";

    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot(RESPONSES_FILE);
        Scanner scanner = new Scanner(System.in);
        boolean isBotMuted = false;
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(DIALOG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.print("Вы: ");
            String input = scanner.nextLine();

            try {
                writer.write("Вы: " + input);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (input.equalsIgnoreCase(STOP_WORD)) {
                isBotMuted = true;
            } else if (input.equalsIgnoreCase(CONTINUE_WORD)) {
                isBotMuted = false;
            } else if (input.equalsIgnoreCase(EXIT_WORD)) {
                break;
            } else if (!isBotMuted) {
                String response = chatBot.getResponse();
                System.out.println("Бот: " + response);

                try {
                    writer.write("Бот: " + response);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("До свидания!");

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatBot {
    private ArrayList<String> responses = new ArrayList<>();
    private Random randomGenerator = new Random();

    public ChatBot(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                responses.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        int index = randomGenerator.nextInt(responses.size());
        return responses.get(index);
    }
}

