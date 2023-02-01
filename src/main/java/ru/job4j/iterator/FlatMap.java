package ru.job4j.iterator;

import java.util.*;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (cursor == null || !cursor.hasNext()) {
            cursor = getNextNonNllOrEmpty(data);
        }
        return cursor.hasNext();
    }

    private Iterator<T> getNextNonNllOrEmpty(Iterator<Iterator<T>> data) {
        while (data.hasNext()) {
            Iterator<T> next = data.next();
            if (next != null && next.hasNext()) {
                return next;
            }
        }
        return cursor;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}