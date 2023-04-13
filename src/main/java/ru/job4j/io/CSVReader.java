package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        File file = new File(argsName.get("path"));
        String[] tempFilAr = argsName.get("filter").split(argsName.get("delimiter"));
        System.out.println("tempFilAr = " + Arrays.toString(tempFilAr));
        List<Integer> index = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] tempAr = scanner.nextLine().split(argsName.get("delimiter"));
                System.out.println("tempAr = " + Arrays.toString(tempAr));
                for (int i = 0; i < tempFilAr.length; i++) {
                    for (int j = 0; j < tempAr.length; j++) {
                        if (tempFilAr[i].equals(tempAr[j])) {
                            index.add(j);
                        }
                    }
                }
                System.out.println("index = " + index);
                for (int i = 0; i < index.size(); i++) {
                    strings.add(i == index.size() - 1 ? tempAr[i] : tempAr[i] + argsName.get("delimiter"));
                }
                strings.add("\n");
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
                        System.out.println("Data written to file");
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
