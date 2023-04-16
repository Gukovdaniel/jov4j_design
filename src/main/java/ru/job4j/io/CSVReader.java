package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        String delimiter = argsName.get("delimiter");
        ArrayList<String> strings = new ArrayList<>();
        File file = new File(argsName.get("path"));
        String[] tempFilAr = argsName.get("filter").split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tempFilAr.length; i++) {
            sb.append(i == tempFilAr.length - 1 ? tempFilAr[i] + "\n  " : tempFilAr[i] + delimiter);
        }
        strings.add(sb.toString());
        List<Integer> index = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            String[] tempAr = scanner.nextLine().split(delimiter);
            for (String s : tempFilAr) {
                for (int j = 0; j < tempAr.length; j++) {
                    if (s.equals(tempAr[j])) {
                        index.add(j);
                    }
                }
            }

            while (scanner.hasNextLine()) {
                tempAr = scanner.nextLine().split(delimiter);
                int cnt = 0;
                for (int i : index) {
                    strings.add(cnt == index.size() - 1 ? tempAr[i] : tempAr[i] + delimiter);
                    cnt++;
                }
                strings.add("\n" + "  ");
            }
        }
        conclusion(argsName, strings);
    }

    private static void conclusion(ArgsName argsName, ArrayList<String> strings) throws IOException {
        File file = new File(argsName.get("out"));
        if (argsName.get("out").contains("stdout")) {
            System.out.println(strings);
        } else {
            if (!file.exists()) {
                file.createNewFile();
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    for (String s : strings) {
                        fos.write(s.getBytes(StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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
    }

    private static void val(ArgsName args) {
        File file = new File(args.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!args.get("path").endsWith(".csv")) {
            System.out.println("Неправильный формат");
        }
        if ((args.get("delimiter").length() > 1)) {
            throw new IllegalArgumentException("Проблема с втрорым параметром. Тут нужно указать разделитель");
        }
        File file1 = new File(args.get("out"));
        if (!file1.exists() || file1.isDirectory()) {
            throw new IllegalArgumentException("Проблема с третьим параметром. Тут нужно указать куда мы сокраняем");
        }
        if (!args.get("filter").contains("name") && !args.get("filter").contains("age")) {
            throw new IllegalArgumentException("Введите название столбцов которые хотите вывести");
        }
    }
}
