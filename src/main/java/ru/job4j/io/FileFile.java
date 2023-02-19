package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileFile {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("data/dataresult.txt")) {
            fileWriter.write(multiple(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String multiple(int size) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                stringBuilder.append(i * j);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }
}
