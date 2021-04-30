package com.company.labs.three.linked;

import java.util.*;

public class SimpleLinked<T> implements Linked<T> {

    private Node first;
    private Node last;
    private int size;

    public SimpleLinked() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
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
        return indexOf(o) >= 0;
    }

    @Override
    public T[] toArray() {
        Object[] temp = new Object[size];
        int currIndex = 0;
        for (T item : this) {
            temp[currIndex] = item;
            ++currIndex;
        }
        return (T[]) temp;
    }

    @Override
    public boolean add(T o) {
        if (o == null) {
            throw new NullPointerException("Object is null");
        }
        if (!isEmpty()) {
            Node<T> prev = last;
            last = new Node<>(o, null);
            prev.next = last;
        } else {
            last = new Node<>(o, null);
            first = last;
        }
        ++size;
        return true;
    }

    @Override
    public boolean addAll(Linked<T> c) {
        int startSize = size;
        for (T item : c) {
            add(item);
        }
        return size != startSize;
    }

    @Override
    public boolean addAll(int index, Linked<T> c) {
        int startSize = size;
        T[] temp = c.toArray();
        for (int i = 0; i < c.size(); i++) {
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
        T[] temp = this.toArray();
        return temp[index];
    }

    @Override
    public T set(int index, T element) {
        // Не получилось через Node сделать , а осталось 30 минут (*(*( приходится делать через глупейший костыль
        T[] temp = this.toArray();
        int defSize = size;
        clear();
        if (index >= 0 && index < defSize) {
            for (int i = 0; i < index; i++) {
                add(temp[i]);
            }
            add(element);
            for (int i = index + 1; i < defSize; i++) {
                add(temp[i]);
            }
        } else {
            throw new IndexOutOfBoundsException("Index not found");
        }
        return temp[index];
    }

    @Override
    public void add(int index, T element) {
        T[] temp = this.toArray();
        int copySizePlusOneElement = size + 1;
        size = 0;
        for (int i = 0; i < index; i++) {
            add(temp[i]);
        }
        add(element);
        for (int i = index + 1; i < copySizePlusOneElement; i++) {
            add(temp[i - 1]);
        }
    }

    @Override
    public T remove(int index) {
        T[] temp = this.toArray();
        T bufRemoveElement = temp[index];
        remove(bufRemoveElement);
        return bufRemoveElement;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        boolean removeFlag = false;
        Node<T> prev = first;
        Node<T> curr = first;
        while (curr.next != null || curr == last) {
            if (curr.element.equals(o)) {
                if (size == 1) {
                    first = null;
                    last = null;
                } else if (curr.equals(first)) {
                    first = first.next;
                } else if (curr.equals(last)) {
                    last = prev;
                    last.next = null;
                } else {
                    prev.next = curr.next;
                }
                --size;
                removeFlag = true;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
        return removeFlag;
    }

    @Override
    public int indexOf(T o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = first; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T o) {
        int index = size;
        if (o == null) {
            for (Node<T> x = last; x != null; x = x.prev) {
                index--;
                if (x.element == null)
                    return index;
            }
        } else {
            for (Node<T> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.element))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public Linked<T> subList(int fromIndex, int toIndex) {
        Linked<T> temp = new SimpleLinked<>();
        T[] buffer = this.toArray();
        if (fromIndex == toIndex) {
            return null;
        } else if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex > toIndex");
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                temp.add(buffer[i]);
            }
        }
        return temp;
    }

    @Override
    public boolean removeAll(Linked<T> c) {
        boolean flag = false;
        int defSize = size;
        T[] temp = this.toArray();
        for (T obj : c) {
            for (int i = 0; i < defSize; i++) {
                if (Objects.equals(temp[i], obj)) {
                    remove(obj);
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean containsAll(Linked<T> c) {
        int defSize = size;
        int currContains = 0;
        T[] temp = this.toArray();
        for (T obj : c) {
            for (int i = 0; i < defSize; i++) {
                if (Objects.equals(temp[i], obj)) {
                    ++currContains;
                    break;
                }
            }
        }
        return currContains == c.size();
    }

    private class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> current = first;

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.element;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>();
    }
}
