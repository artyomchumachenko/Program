package com.company.labs.three.array;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArray<T> implements Array<T> {

    private T[] values;
    private int size = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MULTIPLIER = 2;

    public SimpleArray() {
        this(DEFAULT_SIZE);
    }

    public SimpleArray(int startSize) {
        if (startSize > 0) {
            this.values = (T[]) new Object[startSize];
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
    public boolean contains(T o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.values, this.size);
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(this.values, this.size, a.getClass());
    }

    @Override
    public boolean add(T o) {
        add(size, o);
        return true;
    }

    @Override
    public boolean addAll(Array<T> c) {
        checkCollectionNullException(c);
        int startSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            add(c.get(i));
        }
        return this.size != startSize;
    }

    @Override
    public boolean addAll(int index, Array<T> c) {
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
        this.values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T temp = this.values[index];
        this.values[index] = element;
        return temp;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAddNewElement(index);
        if (size == values.length) {
            values = Arrays.copyOf(values, values.length * MULTIPLIER);
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = element;
        ++size;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T temp = this.values[index];
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
    public int indexOf(T o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(values[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T o) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (Objects.equals(values[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Array<T> subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndexForAddNewElement(toIndex);
        Array<T> temp = new SimpleArray<>();
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
    public boolean removeAll(Array<T> c) {
        checkCollectionNullException(c);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            remove(c.get(i));
        }
        return this.size != prevSize;
    }

    @Override
    public boolean containsAll(Array<T> c) {
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

    private void checkCollectionNullException(Array<T> c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
    }
}
