package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursFindFiles {
    public static void main(String[] args) {
        List<File> fileList = new ArrayList<>();
        searchFiles(new File("."), fileList);
        for (File file : fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static void searchFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] dirFiles = rootFile.listFiles();
            if (dirFiles != null) {
                for (File file : dirFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                    }
                    if (file.getName().toLowerCase().endsWith(".js")) {
                        fileList.add(file);
                    }
                }
            }
        }
    }
}
