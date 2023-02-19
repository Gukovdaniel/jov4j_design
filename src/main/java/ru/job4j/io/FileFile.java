package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileFile {

    public static void main(String[] args) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("data/dataresult.txt");
            fileWriter.write(multiple(4));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String multiple(int size) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                stringBuilder.append(i * j + " ");
            }
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }
}
