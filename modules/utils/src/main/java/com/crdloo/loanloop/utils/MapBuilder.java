package com.crdloo.loanloop.utils;

import java.util.Map;
import java.util.HashMap;

public class MapBuilder<K, V> {
    private HashMap<K, V> map = new HashMap<K, V>();

    public MapBuilder<K, V> append(K key, V value) {
        map.put(key, value);

        return this;
    }

    public Map<K, V> toMap() {
        return map;
    }
}
