package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean hasNext() {
        index = 0;
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                return true;
            } else {
                index++;
                continue;
            }
        }
        return false;
    }

    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index];
    }
}
