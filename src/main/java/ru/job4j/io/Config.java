package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;


public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        final String SP = "=";
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(
                    line -> {
                        int pos = line.indexOf(SP);
                        if (line.contains(SP)) {
                            if (!line.substring(0, pos).isEmpty() && !line.substring(pos + 1).isEmpty()) {
                                values.put(line.substring(0, pos), line.substring(pos + 1));
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } else if (line.isEmpty() || line.contains("#")) {
                                values.put(line, "");
                            } else if (!line.contains(SP) || line.substring(0, pos).isEmpty() || line.substring(pos + 1).isEmpty()) {
                                throw new IllegalArgumentException();
                            }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        throw new UnsupportedOperationException("Don't impl this method yet!");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
