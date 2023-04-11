package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        File file = new File(argsName.get("path"));
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter(argsName.get("delimiter")); // задаем разделитель значений
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(argsName.get("delimiter"));
                String name = fields[0];
                String age = fields[1];
                System.out.println("Name: " + name + ", Age: " + age);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        val(args);
        handle(argsName);
    }

    private static void val(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Некорректые параметры");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!args[0].endsWith(".csv")) {
            System.out.println("Неправильный формат");
        }
        if ((args[1].length() <= 1)) {
            throw new IllegalArgumentException("Проблема с втрорым параметром. Тут нужно указать разделитель");
        }
        File file1 = new File(args[2]);
        if (!file1.exists() && file1.isDirectory()) {
            throw new IllegalArgumentException("Проблема с третьим параметром. Тут нужно указать куда мы сокраняем");
        }
        if (!args[3].contains("name") && !args[3].contains("age")) {
            throw new IllegalArgumentException("Введите название столбцов которые хотите вывести");
        }
    }
}
