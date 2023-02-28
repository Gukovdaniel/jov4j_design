package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<String, FileProperty> fp = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!fp.containsKey(file.getFileName().toString())) {
            fp.put((file.getFileName().toString()), new FileProperty((attrs.size()), file.toAbsolutePath().toString()));
        } else {
            System.out.println(fp.get(file.getFileName().toString()).getName());
        }
        return super.visitFile(file, attrs);
    }
}
