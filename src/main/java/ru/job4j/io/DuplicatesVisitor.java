package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, String> fp = new HashMap<>();
    private Map<FileProperty, List<String>> fpTemp = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!fp.containsKey(new FileProperty((attrs.size()), file.getFileName().toString()))) {
            fp.put(new FileProperty((attrs.size()), file.getFileName().toString()), file.toAbsolutePath().toString());
        } else {
            fpTemp.put(new FileProperty(attrs.size(), file.getFileName().toString()),
                    List.of(file.toAbsolutePath().toString(), fp.get(new FileProperty(attrs.size(), file.getFileName().toString()))));
        }
        return super.visitFile(file, attrs);
    }

    public FileVisitor<? super Path> out(Map<FileProperty, List<String>> map) {
        for (FileProperty fp : map.keySet()) {
            System.out.printf("%s - %s byte \r\n", fp.getName(), fp.getSize());
            map.get(fp).forEach(System.out::println);
        }
        return null;
    }

    public Map<FileProperty, List<String>> getMap() {
        return this.fpTemp;
    }
}
