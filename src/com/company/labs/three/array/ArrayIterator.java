package com.company.labs.three.array;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    private final T[] values;
    private final int size;
    private int index = 0;

    ArrayIterator(T[] values, int size) {
        this.values = values;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public T next() {
        return values[index++];
    }
}
