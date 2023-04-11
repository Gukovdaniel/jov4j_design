package ru.job4j.io;

import java.io.*;
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
                if (argsName.get("out").contains("stdout")) {
                    System.out.println("Name: " + name + ", Age: " + age);
                } else if (file.exists() && !file.isDirectory()) {
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(Integer.parseInt("Name: " + name + ", Age: " + age));
                        System.out.println("Data written to file.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        val(argsName);
        handle(argsName);

        /**
         * Печать результата в консоль надо выполнять,
         * только если по параметру out передано stdout, а если передано другое -
         * то считайте, что передано имя файла, который надо создать и вывести результат в него.
         */
    }

    private static void val(ArgsName args) {
        File file = new File(args.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (args.get("path").endsWith(".csv")) {
            System.out.println("Неправильный формат");
        }
        if ((args.get("delimiter").length() > 1)) {
            throw new IllegalArgumentException("Проблема с втрорым параметром. Тут нужно указать разделитель");
        }
        File file1 = new File(args.get("out"));
        if (!file1.exists() && file1.isDirectory()) {
            throw new IllegalArgumentException("Проблема с третьим параметром. Тут нужно указать куда мы сокраняем");
        }
        if (!args.get("filter").contains("name") && !args.get("filter").contains("age")) {
            throw new IllegalArgumentException("Введите название столбцов которые хотите вывести");
        }
    }
}
