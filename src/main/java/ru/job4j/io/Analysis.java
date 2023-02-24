package ru.job4j.io;

import java.io.*;

public class Analysis {

    public static void unavailable(String source, String target) {
     try (BufferedReader br = new BufferedReader(new FileReader(source));
          PrintWriter pr = new PrintWriter(new FileOutputStream(target))) {
         String c;
         boolean flag = false;
         boolean flagStart = false;
         while ((c = br.readLine()) != null) {
             if (!flag && (c.startsWith("400") || c.startsWith("500"))) {
                 String[] split = c.split(" ");
                 pr.print(split[1] + ";");
                 flag = true;
                 flagStart = true;
             } else if (flagStart && !(c.startsWith("400") || c.startsWith("500"))) {
                 String[] split = c.split(" ");
                 pr.print(split[1]);
                 pr.print(System.lineSeparator());
                 flag = false;
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
