package ru.job4j.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;
    private int outCount = 0;

    public T poll() {
        if (outCount == 0) {
            while (0 < inCount) {
                out.push(in.pop());
                inCount--;
                outCount++;
            }
        }
        outCount--;
        return out.pop();
    }

    public void push(T value) {
        inCount++;
        in.push(value);
    }
}
