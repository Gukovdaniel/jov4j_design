package ru.job4j.io;

import java.io.Console;
import java.io.File;
import java.util.Arrays;

public class ConsoleDemo {

    public static void main(String[] args) {
        String login;
        char[] charPassword;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль недоступна");
            return;
        }
        login = console.readLine("%s", "Введите логин: ");
        console.printf("Ваш логин: %s\n", login);
        charPassword = console.readPassword("%s", "Введите пароль: ");
        System.out.println("Ваш пароль: " + String.valueOf(charPassword));
        Arrays.fill(charPassword, ' ');
        System.out.println("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io");
    }

}