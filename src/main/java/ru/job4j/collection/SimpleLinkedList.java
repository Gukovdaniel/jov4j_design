package ru.job4j.collection;

import java.util.*;

    public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newLink = new Node<>(value, null);
        if (head == null) {
            head = newLink;
        } else {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newLink;
            modCount++;
            size++;
        }
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size + 1);
        Node<E> target = head;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        return target.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> cursor = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = cursor.item;
                cursor = cursor.next;
                return item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
