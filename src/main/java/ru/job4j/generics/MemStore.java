package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;


public class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (model.getId() == id) {
            return false;
        }
            storage.replace(id, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T findById(String id) {
        if (storage.containsKey(id)) {
            return storage.get(id);
        }
        return null;
    }
}
