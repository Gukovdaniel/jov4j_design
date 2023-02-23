package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analysis {

    public void unavailable(String source, String target) {
     try (BufferedReader br = new BufferedReader(new FileReader(source));
          PrintWriter pr = new PrintWriter(new FileOutputStream(target))) {
         List<String> list = new ArrayList<>();
         String c;
         while ((c = br.readLine()) != null) {
             if (c.contains(" ")) {
                 list.add(c);
             }
         }
         String start = null;
         String end = null;
         for (String line : list) {
             if (start == null && (line.startsWith("400") || line.startsWith("500"))) {
                 String[] split = line.split(" ");
                 start = split[1] + ";";
             } else if (start != null && !(line.startsWith("400") || line.startsWith("500"))) {
                 String[] split = line.split(" ");
                 end = split[1];
             }
             if (start != null && end != null) {
                 pr.print(start);
                 pr.print(end + System.lineSeparator());
                 start = null;
                 end = null;
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
