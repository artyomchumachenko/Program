package com.company.labs.three.array;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray implements Array {

    private String[] values;
    private int size = 0;

    public SimpleArray() {
        int defSize = 10;
        values = new String[defSize];
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
    public boolean contains(java.lang.String o) {
        for (String obj : values) {
            if (Objects.equals(obj, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] toArray() {
        String[] temp = new String[size];
        System.arraycopy(values, 0, temp, 0, size);
        return temp;
    }

    @Override
    public boolean add(java.lang.String o) {
        if (size == values.length) {
            String[] temp = values;
            int increaseSize = 2;
            values = new String[size * increaseSize];
            System.arraycopy(temp, 0, values, 0, size);
        }
        values[size] = o;
        ++size;
        return true;
    }

    @Override
    public boolean addAll(Array c) {
        int startSize = size;
        for (String obj : c) {
            add(obj);
        }
        return size != startSize;
    }

    @Override
    public boolean addAll(int index, Array c) {
        int startSize = size;
        String[] temp = c.toArray();
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
    public java.lang.String get(int index) {
        return values[index];
    }

    @Override
    public java.lang.String set(int index, java.lang.String element) {
        if (index <= size && index >= 0) {
            values[index] = element;
        }
        return values[index];
    }

    @Override
    public void add(int index, java.lang.String element) {
        String[] temp = values;
        int copySizePlusOneElement = size + 1;
        size = 0;
        values = new String[temp.length + 1];
        for (int i = 0; i < index; i++) {
            add(temp[i]);
        }
        add(element);
        for (int i = index + 1; i < copySizePlusOneElement; i++) {
            add(temp[i - 1]);
        }
    }

    @Override
    public void remove(int index) {
        String[] temp = values;
        values = new String[temp.length];
        System.arraycopy(temp, 0, values, 0, index);
        --size;
        int amountValuesAfterIndex = size - index;
        System.arraycopy(
                temp, index + 1, // src
                values, index, // target
                amountValuesAfterIndex); // amount
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
    public int indexOf(java.lang.String o) {
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
    public int lastIndexOf(java.lang.String o) {
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
    public Array subList(int fromIndex, int toIndex) {
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
        boolean flag = false;
        for (String obj : c) {
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
    public boolean containsAll(Array c) {
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
    public Iterator<String> iterator() {
        return new ArrayIterator(values, size);
    }
}
