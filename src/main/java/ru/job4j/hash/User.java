package ru.job4j.hash;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        User user = new User("Daniil", 5, calendar);
        int hashcode1 = user.hashCode();
        int hash1 = hashcode1 ^ (hashcode1 >>> 16);
        int bucket = hash1 & 15;
        User user2 = new User("Daniil", 5, calendar);
        int hashcode2 = user2.hashCode();
        int hash2 = hashcode2 ^ (hashcode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(user, new Object());
        map.put(user2, new Object());

        System.out.printf("User хэшкод: %s, хэш: %s, бакет: %s",
                hashcode1, hash1, bucket);
        System.out.println();
        System.out.printf("User хэшкод: %s, хэш: %s, бакет: %s",
                hashcode2, hash2, bucket2);
    }
}
