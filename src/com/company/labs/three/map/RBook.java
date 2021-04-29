package com.company.labs.three.map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RBook<K, V> implements Map<K, V> {
    private int size = 0;
    private static int capacity = 4;
    public Node<K, V>[] hashTable = new Node[capacity];

    private int hash(final K key) {
        return Math.abs(Objects.hashCode(key));
    }

    public class Node<K, V> {
        private List<Node<K, V>> nodes;
        private int hash;
        private K key;
        private V value;
        Node<K, V> next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<K, V>>();
        }

        public List<Node<K, V>> getNodes() {
            return nodes;
        }

        public int hash() {
            return hashCode() % hashTable.length;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int hashCode() {
            return Math.abs(Objects.hashCode(key));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                Node<K, V> node = (Node) obj;
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
    public boolean containsKey(K key) {
        for (Node<K, V> node : hashTable) {
            if (node != null && node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (Node<K, V> node : hashTable) {
            if (node != null && node.value == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hashTemp = hash(key);
        if (hashTable[hashTemp % hashTable.length] != null) {
            Node<K, V> currNode = hashTable[hashTemp % hashTable.length];
            do {
                if (Objects.equals(currNode.getKey(), key)) {
                    return currNode.getValue();
                }
                currNode = currNode.next;
            } while (currNode != null);
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        int index = newNode.hash();
        if (hashTable[index] != null) {
            Node<K, V> currNode = hashTable[index % capacity];
            do {
                if (currNode.key.equals(key)) {
                    V prev = currNode.getValue();
                    currNode.setValue(value);
                    return prev;
                }
                if (currNode.next == null) {
                    Node<K, V> nextNode = new Node<>(null, null);
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
            Node<K, V>[] tempTable = hashTable;
            capacity *= 2;
            hashTable = new Node[capacity];
            size = 0;
            for (Node<K, V> node : tempTable) {
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
    public V remove(K key) {
        Node<K, V> prevNode = null;
        for (Node<K, V> node : hashTable) {
            if (node != null && node.key.equals(key)) {
                V value = node.value;
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
    public void putAll(Map<K, V> map) {
        RBook<K, V> secMap = (RBook) map;
        for (Node<K, V> node : secMap.hashTable) {
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
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int countArray = 0;
            int valuesCounter = 0;
            Iterator<Node<K, V>> subIterator = null;

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

            public V next() {
                ++valuesCounter;
                return (V) subIterator.next().getValue();
            }
        };
    }
}
