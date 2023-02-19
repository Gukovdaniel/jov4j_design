package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileFile {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int[] s1 : multiple(5)) {
            sb.append(Arrays.toString(s1)).append('\n');
        }
        String s = sb.toString();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("data/dataresult.txt");
            fileWriter.write(s);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] multiple(int size) {
        int[][] multiple = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                multiple[i][j] = (i + 1) * (j + 1);
            }
        }
        return multiple;
    }
}
