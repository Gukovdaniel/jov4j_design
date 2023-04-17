package assertj;

import assertj.ChatBot;

import java.util.Scanner;

public class ConsoleChat {
    public static void main(String[] args) {
        assertj.ChatBot chatBot = new ChatBot("data/respounces.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Вы: ");
            String input = scanner.nextLine();

            String response = chatBot.getResponse();
            System.out.println("Бот: " + response);
        }
    }
}
