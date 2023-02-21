package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("data/log.txt"));
        String c;
        while ((c = bufferedReader.readLine()) != null) {
            String[] parts = c.split(" ");
            if (parts[parts.length - 2].equals("404")) {
                list.add(c + " \r\n");
            }
        }
        bufferedReader.close();
        return list;
    }


    public static void main(String[] args) throws IOException {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);

    }
}
