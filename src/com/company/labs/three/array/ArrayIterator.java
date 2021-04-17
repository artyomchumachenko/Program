package com.company.labs.three.array;

import java.util.Iterator;

public class ArrayIterator implements Iterator<String> {
    private String[] values;
    private int size;
    private int index = 0;

    ArrayIterator(String[] values, int size) {
        this.values = values;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public String next() {
        return values[index++];
    }
}
