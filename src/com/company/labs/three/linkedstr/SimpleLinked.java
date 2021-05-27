package com.company.labs.three.linkedstr;

import java.util.Objects;

public class SimpleLinked implements Linked {

    private Node first;
    private Node last;
    private int size;

    public SimpleLinked() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        private String element;
        private Node next;

        public Node(String element, Node next) {
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
    public boolean contains(String o) {
        return indexOf(o) != -1;
    }

    @Override
    public String[] toArray() {
        String[] temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = get(i);
        }
        return temp;
    }

    @Override
    public boolean add(String o) {
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(o, null);
            prev.next = last;
        } else {
            last = new Node(o, null);
            first = last;
        }
        ++size;
        return true;
    }

    @Override
    public boolean addAll(Linked c) {
        checkNullLinkedException(c);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            add(c.get(i));
        }
        return this.size == prevSize;
    }

    @Override
    public boolean addAll(int index, Linked c) {
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
    public String get(int index) {
        checkIndex(index);
        Node currNode = first;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.element;
    }

    @Override
    public String set(int index, String element) {
        checkIndex(index);
        Node currNode = first;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        String buffer = currNode.element;
        currNode.element = element;
        return buffer;
    }

    @Override
    public void add(int index, String element) {
        checkIndexForAddNewElement(index);
        if (index == 0) {
            Node currNode = new Node(element, first);
            first = currNode;
            ++size;
        } else {
            Node prevNode = first;
            for (int i = 0; i < index - 1; i++) {
                prevNode = prevNode.next;
            }
            Node currNode = new Node(element, prevNode.next);
            prevNode.next = currNode;
            if (index == size) {
                last = currNode;
            }
            ++size;
        }
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        if (index == 0) {
            String buffer = first.element;
            first = first.next;
            --size;
            return buffer;
        } else {
            Node prevNode = first;
            for (int i = 0; i < index - 1; i++) {
                prevNode = prevNode.next;
            }
            String buffer = prevNode.next.element;
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, get(i))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), o)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), o)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Linked subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex);
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        Linked buffer = new SimpleLinked();
        Node currNode = first;
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
    public boolean removeAll(Linked c) {
        checkNullLinkedException(c);
        int prevSize = this.size;
        for (int i = 0; i < c.size(); i++) {
            remove(c.get(i));
        }
        return this.size != prevSize;
    }

    @Override
    public boolean containsAll(Linked c) {
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

    private void checkNullLinkedException(Linked c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
    }
}
