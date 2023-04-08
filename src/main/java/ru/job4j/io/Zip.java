package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> listPaths = new ArrayList<>();
        try {
            listPaths = Search.search(Path.of(argsName.get("d")),
                    p -> !p.toFile()
                            .getName()
                            .endsWith(argsName.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Zip().packFiles(listPaths, new File(argsName.get("o")));

    }

    private static void validate(ArgsName argsName) {
        File file = new File(argsName.get("d"));
        if (!argsName.get("e").endsWith(".") && argsName.get("e").length() != 1) {
            System.out.println("Неправильный ввод параметра класса");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            System.out.println("Неправильный ввод создаваемого архива");
        }
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("Неправильный ввод пути директории для архивации");
        }
    }
}
