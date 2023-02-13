package ru.job4j.hash;

import java.util.*;

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
        Calendar calendar = new GregorianCalendar(1992, 0, 6, 17, 5, 00);
        User user = new User(null, 5, calendar);
        int hashcode1 = user.hashCode();
        int hash1 = hashcode1 ^ (hashcode1 >>> 16);
        int bucket = hash1 & 15;
        User user2 = new User("L", 5, calendar);
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
        System.out.println();
        System.out.println(user == user2);
        System.out.println(map.size());
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        User user = (User) o;
//        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
