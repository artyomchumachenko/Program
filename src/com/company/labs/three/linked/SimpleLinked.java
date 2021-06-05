package com.company.labs.three.linked;

import java.util.Objects;

public class SimpleLinked<T> implements Linked<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public SimpleLinked() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node<T> {
        private T element;
        private Node<T> next;

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
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[size];
        for (int i = 0; i < size; i++) {
            temp[i] = get(i);
        }
        return temp;
    }

    @Override
    public boolean add(T o) {
        if (!isEmpty()) {
            Node<T> prev = last;
            last = new Node<T>(o, null);
            prev.next = last;
        } else {
            last = new Node<T>(o, null);
            first = last;
        }
        ++size;
        return true;
    }

    @Override
    public boolean addAll(Linked<T> c) {
        checkNullLinkedException(c);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            add(c.get(i));
        }
        return this.size == prevSize;
    }

    @Override
    public boolean addAll(int index, Linked<T> c) {
        checkNullLinkedException(c);
        checkIndexForAddNewElement(index);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            add(index + i, c.get(i));
        }
        return this.size == prevSize;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> currNode = first;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.element;
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
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
        checkIndexForAddNewElement(index);
        if (index == 0) {
            Node<T> currNode = new Node<>(element, first);
            first = currNode;
            if (size == 0) {
                last = currNode;
            }
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
        checkIndex(index);
        if (index == 0) {
            T buffer = first.element;
            first = first.next;
            --size;
            if (size == 0) {
                last = null;
            }
            return buffer;
        } else {
            Node<T> prevNode = first;
            for (int i = 0; i < index - 1; i++) {
                prevNode = prevNode.next;
            }
            T buffer = prevNode.next.element;
            prevNode.next = prevNode.next.next;
            if (index == (size - 1)) {
                last = prevNode;
            }
            --size;
            return buffer;
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, get(i))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), o)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(T o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), o)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Linked<T> subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndexForAddNewElement(toIndex);
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        Linked<T> buffer = new SimpleLinked<>();
        Node<T> currNode = first;
        for (int i = 0; i < fromIndex; i++) {
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
        checkNullLinkedException(c);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            remove(c.get(i));
        }
        return this.size != prevSize;
    }

    @Override
    public boolean containsAll(Linked<T> c) {
        checkNullLinkedException(c);
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

    private void checkNullLinkedException(Linked<T> c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
    }
}
