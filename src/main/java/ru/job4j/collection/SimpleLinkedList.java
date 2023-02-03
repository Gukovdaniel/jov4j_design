package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> tail;

    @Override
    public void add(E value) {
        Node<E> newLink = new Node<>(value, null);
        if (head == null) {
            head = newLink;
        } else {
           tail.next = newLink;
        }
        tail = newLink;
        modCount++;
        size++;
    }


    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> target = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            target = target.next;
        }
        return target.item;
    }

    private Node<E> getNextEl(Node<E> current) {
        return current.next;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> cursor = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkModCount();
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

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
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
