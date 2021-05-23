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
        Object[] outBuffer = new Object[size];
        for (int i = 0; i < size; i++) {
            outBuffer[i] = get(i);
        }
        return (T[]) outBuffer;
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
        Node<T> currNode = first;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.element;
    }

    @Override
    public T set(int index, T element) {
        Node<T> currNode = first;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        T buffer = currNode.element;
        currNode.element = element;
        return buffer;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0) {
            Node<T> currNode = new Node<>(element, first);
            first = currNode;
            ++size;
        } else {
            Node<T> prevNode = first;
            for (int i = 0; i < index - 1; i++) {
                prevNode = prevNode.next;
            }
            Node<T> currNode = new Node<>(element, prevNode.next);
            prevNode.next = currNode;
            if (index == size) {
                last = currNode;
            }
            ++size;
        }
    }

    @Override
    public T remove(int index) {
        if (index == 0) {
            T buffer = (T) first.element;
            first = first.next;
            --size;
            return buffer;
        } else {
            Node<T> prevNode = first;
            for (int i = 0; i < index - 1; i++) {
                prevNode = prevNode.next;
            }
            T buffer = prevNode.next.element;
            prevNode.next = prevNode.next.next;
            if (index == size) {
                last = prevNode;
            }
            --size;
            return buffer;
        }
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
        Linked<T> buffer = new SimpleLinked<>();
        Node<T> currNode = first;
        for (int i = 0; i < fromIndex - 1; i++) {
            currNode = currNode.next;
        }
        for (int i = fromIndex; i < toIndex; i++) {
            buffer.add(currNode.element);
            currNode = currNode.next;
        }
        return buffer;
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
        for (int i = 0; i < c.size(); i++) {
            if (!contains(c.get(i))) {
                return false;
            }
        }
        return true;
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
