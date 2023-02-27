package ru.job4j.io;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class DuplicateFinder {
    public static void main(String[] args) throws IOException {

        Files.walkFileTree(Path.of("."), new DuplicatesVisitor());
        DuplicatesVisitor dv = new DuplicatesVisitor();
        dv.console();
    }
}
