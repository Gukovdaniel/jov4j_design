package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {

            File file = new File(".");

            if (!file.exists()) {
                throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
            }
            if (!file.isDirectory()) {
                throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
            }
            for (File subfile : file.listFiles()) {
                System.out.printf("Имя файла : %s,\r\nРазмер файла : %d ", subfile.getAbsoluteFile().getName(), file.getTotalSpace());

        }
    }
}
