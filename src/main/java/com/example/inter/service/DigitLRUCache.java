package com.example.inter.service;

import java.util.*;

public class DigitLRUCache {

    Map<List, Integer> cache;
    List<List> orderCache;
    int capacity;

    public DigitLRUCache(int capacity) {
        this.cache = new LinkedHashMap<List, Integer>(capacity);
        this.orderCache = new LinkedList<List>();
        this.capacity = capacity;
    }

    public Integer get(List key) {
        if (!orderCache.contains(key))
            return -1;

        orderCache.remove(key);
        orderCache.add(key);
        return cache.get(key);
    }

    public void put(List key, Integer value){
        if(orderCache.size()>= 10){
            cache.remove(orderCache.get(0));
            orderCache.remove(0);
        }
        cache.put(key, value);
        orderCache.add(key);
    }
}
