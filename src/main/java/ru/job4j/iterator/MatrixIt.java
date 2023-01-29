package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int total = 0;
        while (row < data.length && column < data[row].length) {
            for (int[] rowData: data) {
                row++;
                for (int cell : rowData) {
                    column++;
                    total = cell;

                }
            }
        }
        return Arrays.asList(data).contains(total);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column];
    }
}
