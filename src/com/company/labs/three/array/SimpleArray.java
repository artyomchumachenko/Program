package com.company.labs.three.array;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Array<T> {

    private Object[] values;
    private int size = 0;

    public SimpleArray() {
        int defSize = 10;
        values = new Object[defSize];
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
        for (Object obj : values) {
            if (Objects.equals(obj, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] toArray() {
        Object[] temp = new Object[size];
        System.arraycopy(values, 0, temp, 0, size);
        return (T[]) temp;
    }

    @Override
    public boolean add(T o) {
        if (size == values.length) {
            Object[] temp = values;
            int increaseSize = 2;
            values = new String[size * increaseSize];
            System.arraycopy(temp, 0, values, 0, size);
        }
        values[size] = o;
        ++size;
        return true;
    }

    @Override
    public boolean addAll(Array<T> c) {
        int startSize = size;
        for (T obj : c) {
            add(obj);
        }
        return size != startSize;
    }

    @Override
    public boolean addAll(int index, Array<T> c) {
        int startSize = size;
        T[] temp = c.toArray();
        for (int i = 0; i < temp.length; i++) {
            add(index + i, temp[i]);
        }
        return size != startSize;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public T get(int index) {
        return (T) values[index];
    }

    @Override
    public T set(int index, T element) {
        if (index <= size && index >= 0) {
            values[index] = element;
        }
        return (T) values[index];
    }

    @Override
    public void add(int index, T element) {
        Object[] temp = values;
        int copySizePlusOneElement = size + 1;
        size = 0;
        values = new Object[temp.length + 1];
        for (int i = 0; i < index; i++) {
            add((T) temp[i]);
        }
        add(element);
        for (int i = index + 1; i < copySizePlusOneElement; i++) {
            add((T) temp[i - 1]);
        }
    }

    @Override
    public T remove(int index) {
        Object[] temp = values;
        Object val = temp[index];
        values = new Object[temp.length];
        System.arraycopy(temp, 0, values, 0, index);
        --size;
        int amountValuesAfterIndex = size - index;
        System.arraycopy(
                temp, index + 1, // src
                values, index, // target
                amountValuesAfterIndex); // amount
        return (T) val;
    }

    @Override
    public boolean remove(Object o) {
        for (int currStep = 0; currStep < size; currStep++) {
            if (values[currStep].equals(o)) {
                remove(currStep);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T o) {
        if (size != 0) {
            for (int currStep = 0; currStep < size; currStep++) {
                if (values[currStep].equals(o)) {
                    return currStep;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T o) {
        if (size != 0) {
            for (int currStep = size - 1; currStep >= 0; currStep--) {
                if (values[currStep].equals(o)) {
                    return currStep;
                }
            }
        }
        return -1;
    }

    @Override
    public Array<T> subList(int fromIndex, int toIndex) {
        Array<T> temp = new SimpleArray<>();
        if (fromIndex == toIndex) {
            throw new IndexOutOfBoundsException("fromIndex = toIndex error");
        } else if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex > toIndex error");
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                temp.add((T) values[i]);
            }
        }
        return temp;
    }

    @Override
    public boolean removeAll(Array<T> c) {
        boolean flag = false;
        for (T obj : c) {
            for (int i = 0; i < size; i++) {
                if (values[i].equals(obj)) {
                    remove(i);
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean containsAll(Array<T> c) {
        if (size == c.size()) {
            for (int i = 0; i < size; i++) {
                if (!(values[i].equals(c.toArray()[i]))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>((T[]) values, size);
    }
}
