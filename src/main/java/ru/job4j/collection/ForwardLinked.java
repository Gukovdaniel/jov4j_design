package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> tNode = new Node<>(value, null);
        if (head == null) {
            head = tNode;
        } else {
            Node<T> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = tNode;
            size++;
            modCount++;
        }
    }

    public T get(int index) {
        Objects.checkIndex(index, size + 1);
        Node<T> target = head;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        return target.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            T rsl = head.item;
            head = head.next;
            return rsl;
        }
    }

    @Override
    public Iterator<T> iterator() {
       return new Iterator<T>() {
           private Node<T> cursor = head;
           private int expectedMod = modCount;

           @Override
           public boolean hasNext() {
               if (expectedMod != modCount) {
                   throw new ConcurrentModificationException();
               }
               return cursor != null;
           }

           @Override
           public T next() {
              if (!hasNext()) {
                  throw new NoSuchElementException();
              }
              T item = cursor.item;
              cursor = cursor.next;
              return item;
           }
       };
    }

    private static class Node<T> {
       private T item;
       private Node<T> next;

        public Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }

}
