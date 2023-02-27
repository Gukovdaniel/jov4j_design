package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    List<FileProperty> fp = new ArrayList<>();
    Set<FileProperty> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        fp.add(new FileProperty((attrs.size()), file.getFileName().toString()));
        return super.visitFile(file, attrs);
    }

    public void console() {
        fp.stream()
                .filter(e -> !set.add(e))
                .collect(Collectors.toSet());
        for (FileProperty f : set) {
            System.out.println(f.getName());
        }
    }
}
