package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T temp = linked.getHead();
       linked.deleteFirst();
       return temp;
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
