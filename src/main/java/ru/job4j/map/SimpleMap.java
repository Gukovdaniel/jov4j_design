package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];


    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[index(key)] == null) {
            table[index(key)] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    public int index(K key) {
        return (key == null) ? 0 : indexFor(hash(key.hashCode()));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
    capacity *= 2;
    MapEntry<K, V>[] temp = new MapEntry[capacity];
    for (MapEntry<K, V> t : table) {
        if (t != null) {
            temp[index(t.key)] = t;
        }
    }
    table = temp;
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> mapEntry = table[index(key)];
        return mapEntry == null ? null : mapEntry.value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = true;
        MapEntry<K, V> mapEntry = table[index(key)];
        if (mapEntry == null || !Objects.equals(key, mapEntry.key)) {
            rsl = false;
        }
        table[index(key)] = null;
        modCount++;
        count--;
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private int expectedMoodCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedMoodCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
