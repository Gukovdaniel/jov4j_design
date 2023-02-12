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
        int index = hash(key.hashCode());
        int i = indexFor(index);
        if (table[i] == null) {
            if (count >= capacity * LOAD_FACTOR) {
                expand();
            }
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
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
            temp[hash(t.hashCode())] = t;
        }
    }
    table = temp;
    }

    @Override
    public V get(K key) {
        int index = hash(key.hashCode());
        int i = indexFor(index);
        if (table[i] != null && table[i].key.equals(key)) {
            return table[i].value;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(K key) {
        int index = hash(key.hashCode());
        int i = indexFor(index);
        if (table[i] != null && table[i].key.equals(key)) {
            table[i] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private final int expectMod = modCount;

            @Override
            public boolean hasNext() {
                if (expectMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[index] == null) {
                    index++;
                }
                return (K) table[index++].value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity && count == simpleMap.count && modCount == simpleMap.modCount && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, count, modCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }
}
