package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String c;
            while ((c = bufferedReader.readLine()) != null) {
                String[] parts = c.split(" ");
                if ("404".equals(parts[parts.length - 2])) {
                    list.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(file)) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("data/log.txt");
        save(log, "data/404.txt");
    }
}

