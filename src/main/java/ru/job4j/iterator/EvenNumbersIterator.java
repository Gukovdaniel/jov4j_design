package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean hasNext() {
        while (index < (data.length) && (data[index] % 2) != 0) {
            index++;
            }
        return index < data.length;
    }

    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
    /**
     * В заголовке цикла while разместите условия -
     * что индекс не вышел за границу массива и что
     * элемент по этому индексу нечетный.
     * Если это так, то в теле цикла перейдите на следующий элемент,
     * если нет - то выйдите из цикла и в операторе return проверьте,
     * что индекс не вышел за границу массива.
     */
}
