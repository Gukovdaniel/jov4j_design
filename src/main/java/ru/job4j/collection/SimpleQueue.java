package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int inCount = 0;
    int outCount = 0;

    public T poll() {
        while (0 < inCount) {
            out.push(in.pop());
            inCount--;
            outCount++;
        }
        return out.pop();
    }

    public void push(T value) {
        inCount++;
        in.push(value);
    }
}
