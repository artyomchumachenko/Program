package com.company.labs.three.arraystr;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArray implements Array {

    private String[] values;
    private int size = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MULTIPLIER = 2;

    public SimpleArray() {
        this(DEFAULT_SIZE);
    }

    public SimpleArray(int startSize) {
        if (startSize > 0) {
            values = new String[startSize];
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(String o) {
        return indexOf(o) != -1;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(this.values, this.size);
    }

    @Override
    public boolean add(String o) {
        add(size, o);
        return true;
    }

    @Override
    public boolean addAll(Array c) {
        checkCollectionNullException(c);
        int startSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            add(c.get(i));
        }
        return this.size != startSize;
    }

    @Override
    public boolean addAll(int index, Array c) {
        checkCollectionNullException(c);
        checkIndexForAddNewElement(index);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            add(index + i, c.get(i));
        }
        return this.size != prevSize;
    }

    @Override
    public void clear() {
        size = 0;
        this.values = new String[DEFAULT_SIZE];
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public String set(int index, String element) {
        checkIndex(index);
        String temp = this.values[index];
        this.values[index] = element;
        return temp;
    }

    @Override
    public void add(int index, String element) {
        checkIndexForAddNewElement(index);
        if (size == values.length) {
            values = Arrays.copyOf(values, values.length * MULTIPLIER);
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = element;
        ++size;
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String temp = this.values[index];
        --size;
        System.arraycopy(values, index + 1, values, index, size - index);
        values[size] = null;
        return temp;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(values[i], o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(values[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String o) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (Objects.equals(values[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Array subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndexForAddNewElement(toIndex);
        Array temp = new SimpleArray();
        if (fromIndex == toIndex) {
            throw new IndexOutOfBoundsException("fromIndex = toIndex error");
        } else if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex > toIndex error");
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                temp.add(values[i]);
            }
        }
        return temp;
    }

    @Override
    public boolean removeAll(Array c) {
        checkCollectionNullException(c);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            remove(c.get(i));
        }
        return this.size != prevSize;
    }

    @Override
    public boolean containsAll(Array c) {
        checkCollectionNullException(c);
        for (int i = 0; i < c.size(); i++) {
            if (!contains(c.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkIndexForAddNewElement(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkCollectionNullException(Array c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
    }
}
