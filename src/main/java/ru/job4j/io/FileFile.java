package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileFile {


    public static void main(String[] args) {
        String s = Arrays.deepToString(multiple(4));
            try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
                out.write(s.getBytes());
                out.write(System.lineSeparator().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static int[][] multiple(int size) {
        int[][] multiple = new int[size][size];
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                multiple[i][j] = (i + 1) * (j + 1);
            }
        }
        return multiple;
    }
}
