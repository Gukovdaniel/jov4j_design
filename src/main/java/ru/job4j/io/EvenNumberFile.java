package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
           String even = String.valueOf(text);
            int[] numArr = Arrays.stream(even.split("\r\n")).mapToInt(Integer::parseInt).toArray();
            for (int temp : numArr) {
                if (temp % 2 == 0) {
                    System.out.println(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
