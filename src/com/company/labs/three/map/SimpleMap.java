package com.company.labs.three.map;

import java.util.Iterator;

public class SimpleMap implements Map {

    public static void main(String[] args) {
        System.out.println("Hello");
    }

    private Node[] hashTable;
    private int size = 0;

    public SimpleMap() {
        hashTable = new Node[16];

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(String key) {
        return false;
    }

    @Override
    public boolean containsValue(Integer value) {
        return false;
    }

    @Override
    public Integer get(String key) {
        return null;
    }

    @Override
    public Integer put(String key, Integer value) {
        return null;
    }

    @Override
    public Integer remove(String key) {
        return null;
    }

    @Override
    public void putAll(Map map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
