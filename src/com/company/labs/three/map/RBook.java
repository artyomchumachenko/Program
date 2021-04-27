package com.company.labs.three.map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RBook implements Map {
    private int size = 0;
    private static int capacity = 4;
    Node[] hashTable = new Node[capacity];

    public class Node {
        private List<Node> nodes;
        private int hash;
        private String key;
        private Integer value;
        Node next;

        private Node(String key, Integer value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node>();
        }

        private List<Node> getNodes() {
            return nodes;
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        private String getKey() {
            return key;
        }

        private Integer getValue() {
            return value;
        }

        private void setValue(Integer value) {
            this.value = value;
        }

        public int hashCode() {
            int hash = 0;
            if (key != null) {
                for (char element : key.toCharArray()) {
                    hash += (int) element;
                }
            }
            return hash;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                Node node = (Node) obj;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }
            return false;
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
    public boolean containsKey(String key) {
        for (Node node : hashTable) {
            if (node != null && node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Integer value) {
        for (Node node : hashTable) {
            if (node != null && node.value == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(String key) {
        for (Node node : hashTable) {
            if (node != null && node.key == key) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public Integer put(String key, Integer value) {
        Node newNode = new Node(key, value);
        int index = newNode.hash();
        if (hashTable[index % capacity] != null) {
            Node currNode = hashTable[index % capacity];
            do {
                if (currNode.key.equals(key)) {
                    int prev = currNode.value;
                    currNode.value = value;
                    return prev;
                }
                if (currNode.next == null) {
                    Node nextNode = new Node(null, null);
                    nextNode.value = value;
                    nextNode.key = key;
                    nextNode.hash = index;
                    currNode.next = nextNode;
                    ++size;
                }
                currNode = currNode.next;
            } while (currNode.next != null);
        } else {
            newNode.key = key;
            newNode.value = value;
            newNode.hash = index;
            newNode.next = null;
            ++size;
            hashTable[index % capacity] = newNode;
        }
        if (size == capacity) {
            Node[] tempBuckets = hashTable;
            capacity *= 2;
            hashTable = new Node[capacity];
            size = 0;
            for (Node node : tempBuckets) {
                if (node != null) {
                    do {
                        put(node.key, node.value);
                        node = node.next;
                    } while (node != null);
                }
            }
        }
        return null;
    }

    @Override
    public Integer remove(String key) {
        Node prevNode = null;
        for (Node node : hashTable) {
            if (node != null && node.key.equals(key)) {
                int value = node.value;
                if (prevNode != null && node.next != null) {
                    prevNode.next = node.next;
                } else if (prevNode == null) {
                    hashTable[node.hash % capacity] = node.next;
                }
                node = null;
                --size;
                return value;
            }
            prevNode = node;
        }
        return null;
    }

    @Override
    public void putAll(Map map) {
        RBook secMap = (RBook) map;
        for (Node node : secMap.hashTable) {
            if (node != null) {
                put(node.key, node.value);
            }
        }
    }

    @Override
    public void clear() {
        hashTable = new Node[capacity];
        size = 0;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<Integer>() {
            int countArray = 0;
            int valuesCounter = 0;
            Iterator<Node> subIterator = null;

            public boolean hasNext() {
                if (valuesCounter == size) {
                    return false;
                }
                if (subIterator == null || !subIterator.hasNext()) {
                    if (moveToNextCell()) {
                        subIterator = hashTable[countArray].getNodes().iterator();
                    } else {
                        return false;
                    }
                }
                return subIterator.hasNext();
            }

            private boolean moveToNextCell() {
                ++countArray;
                while (hashTable[countArray] == null) {
                    ++countArray;
                }
                return hashTable[countArray] != null;
            }

            public Integer next() {
                ++valuesCounter;
                return (Integer) subIterator.next().getValue();
            }
        };
    }
}
