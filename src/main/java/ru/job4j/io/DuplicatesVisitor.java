package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, String> fp = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!fp.containsKey(new FileProperty((attrs.size()), file.getFileName().toString()))) {
            fp.put(new FileProperty((attrs.size()), file.getFileName().toString()), file.toAbsolutePath().toString());
        } else {
            out(file, attrs);
        }
        return super.visitFile(file, attrs);
    }

    private void out(Path file, BasicFileAttributes attrs) {
        System.out.printf("%s - %s byte \r\n", file.getFileName(), attrs.size());
        System.out.println(file.toAbsolutePath());
        System.out.println(fp.get(new FileProperty((attrs.size()), file.getFileName().toString())));
    }

    public Map<FileProperty, String> getFp() {
        return fp;
    }
}
