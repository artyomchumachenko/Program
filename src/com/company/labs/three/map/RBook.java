package com.company.labs.three.map;

import java.util.Objects;

public class RBook<K, V> implements Map<K, V> {
    private Node<K, V>[] hashTable;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_MAX_USAGE_PERCENT = 0.7;
    private int currCapacity;
    private double maxUsePercent;

    public RBook() {
        this(DEFAULT_CAPACITY, DEFAULT_MAX_USAGE_PERCENT);
    }

    public RBook(int startCapacity) {
        this(startCapacity, DEFAULT_MAX_USAGE_PERCENT);
    }

    public RBook(int currCapacity, double maxUsePercent) {
        if (currCapacity < 1 || maxUsePercent < 0 || maxUsePercent > 1) {
            throw new IllegalArgumentException();
        } else {
            hashTable = new Node[currCapacity];
            this.currCapacity = currCapacity;
            this.maxUsePercent = maxUsePercent;
        }
    }

    public class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        Node<K, V> next;

        private Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private V setValue(V newValue) {
            V prevVal = this.value;
            this.value = newValue;
            return prevVal;
        }

        public int getHash() {
            return hash;
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
        int hash = calcHash(key);
        if (hashTable[hash % hashTable.length] != null) {
            Node<K, V> currNode = hashTable[hash % hashTable.length];
            do {
                if (Objects.equals(currNode.getKey(), key)) {
                    return true;
                }
                currNode = currNode.next;
            } while (currNode != null);
        }
        return false;
    }

    public Object[] getAllKeys() {
        Object[] tmp = new Object[size];
        int index = 0;
        for (Node<K, V> currNode : hashTable) {
            if (currNode != null) {
                do {
                    tmp[index++] = currNode.getKey();
                    currNode = currNode.next;
                } while (currNode != null);
            }
        }
        return tmp;
    }

    @Override
    public boolean containsValue(V value) {
        for (Node<K, V> currNode : hashTable) {
            if (currNode != null) {
                do {
                    if (Objects.equals(currNode.getValue(), value)) {
                        return true;
                    }
                    currNode = currNode.next;
                } while (currNode != null);
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hash = calcHash(key);
        if (hashTable[hash % hashTable.length] != null) {
            Node<K, V> currNode = hashTable[hash % hashTable.length];
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
        int hash = calcHash(key);
        Node<K, V> newNode = new Node<>(hash, key, value);
        if (hashTable[hash % hashTable.length] == null) {
            hashTable[hash % hashTable.length] = newNode;
            ++size;
        } else {
            Node<K, V> currNode = hashTable[hash % hashTable.length];
            do {
                if (Objects.equals(currNode.getKey(), key)) {
                    V prevValue = currNode.setValue(value);
                    return prevValue;
                }
                if (currNode.next == null) {
                    currNode.next = newNode;
                    ++size;
                    break;
                }
                currNode = currNode.next;
            } while (currNode != null);
        }
        if (size > currCapacity * maxUsePercent) {
            hashTable = resize();
        }
        return null;
    }

    private Node<K, V>[] resize() {
        Node<K, V>[] oldBaskets = hashTable;
        currCapacity *= 2;
        Node<K, V>[] newBaskets = new Node[currCapacity];

        for (Node<K, V> oldBasket : oldBaskets) {
            if (oldBasket != null) {
                Node<K, V> currNode = oldBasket;
                do {
                    if (newBaskets[currNode.getHash() % newBaskets.length] == null) {
                        newBaskets[currNode.getHash() % newBaskets.length] = new Node<>(currNode.hash, currNode.key, currNode.value);
                    } else {
                        Node<K, V> nodeToComplete = newBaskets[currNode.getHash() % newBaskets.length];
                        do {
                            if (nodeToComplete.next == null) {
                                nodeToComplete.next = new Node<>(currNode.hash, currNode.key, currNode.value);
                                break;
                            }
                            nodeToComplete = nodeToComplete.next;
                        } while (nodeToComplete != null);
                    }
                    currNode = currNode.next;
                } while (currNode != null);
            }
        }
        return newBaskets;
    }

    @Override
    public V remove(K key) {
        int hash = calcHash(key);
        if (hashTable[hash % hashTable.length] != null) {
            Node<K, V> currNode = hashTable[hash % hashTable.length];
            Node<K, V> prevNode = null;
            do {
                if (Objects.equals(currNode.getKey(), key)) {
                    V removedValue = currNode.value;
                    if (prevNode == null) {
                        hashTable[hash % hashTable.length] = currNode.next;
                    } else {
                        prevNode.next = currNode.next;
                    }
                    --size;
                    return removedValue;
                }
                prevNode = currNode;
                currNode = currNode.next;
            } while (currNode != null);
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        if (map instanceof RBook) {
            RBook<K, V> map2 = (RBook<K, V>) map;
            for (Node<K, V> node : map2.hashTable) {
                if (node != null) {
                    do {
                        put(node.getKey(), node.getValue());
                        node = node.next;
                    } while (node != null);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void clear() {
        hashTable = new Node[hashTable.length];
        size = 0;
    }

    private int calcHash(K key) {
        return Math.abs(Objects.hashCode(key));
    }
}
