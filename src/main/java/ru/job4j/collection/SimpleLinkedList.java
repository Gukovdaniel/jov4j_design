package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (this.head == null) {
           this.head = newNode;
           return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
            this.size++;
        this.modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = this.head;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return head.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            Node<E> current = null;
            Node<E> prev = null;
            Node<E> ensuing = head;

            @Override
            public boolean hasNext() {
                checkModCount();
                return ensuing != null;
            }

            @Override
            public E next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                prev = current;
                current = ensuing;
                ensuing = ensuing.next;
                return null;
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
