package com.company.labs.three.array;

import java.util.Iterator;

public class IteratorTutorial {
}

class SimpleIterator<T> implements Iterator<T> {

    private T[] objects;
    private int index = 0;

    SimpleIterator(T[] objects) {
        this.objects = objects;
    }

    @Override
    public boolean hasNext() {
        return index < objects.length;
    }

    @Override
    public T next() {
        return objects[index++];
    }

    public static void main(String[] args) {
        String[] strings = new String[3];
        strings[0] = "Hello";
        strings[1] = "my";
        strings[2] = "friend!";
        SimpleIterator<String> s = new SimpleIterator<>(strings);
        while (s.hasNext()) {
            String i = s.next();
            System.out.println(i);
        }
    }
}
