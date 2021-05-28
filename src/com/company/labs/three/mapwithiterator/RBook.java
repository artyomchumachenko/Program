package com.company.labs.three.mapwithiterator;

import java.util.Iterator;
import java.util.Objects;

public class RBook implements Map {
    private Node[] hashTable;
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

    private int hash(Node node) {
        return node.hashCode() % hashTable.length;
    }

    private int hash(final String key) {
        int hash = 0;
        if (key != null) {
            for (char element : key.toCharArray()) {
                hash += (int) element;
            }
        }
        return hash;
    }

    public class Node {
        private int hash;
        private String key;
        private Integer value;
        Node next;

        private Node(int hash, String key, Integer value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
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

        private Integer setValue(Integer newValue) {
            Integer prevVal = this.value;
            this.value = newValue;
            return prevVal;
        }

        public int getHash() {
            return hash;
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
        int hash = calcHash(key);
        if (hashTable[hash % hashTable.length] != null) {
            Node currNode = hashTable[hash % hashTable.length];
            do {
                if (Objects.equals(currNode.getKey(), key)) {
                    return true;
                }
                currNode = currNode.next;
            } while (currNode != null);
        }
        return false;
    }

    @Override
    public boolean containsValue(Integer value) {
        for (Node currNode : hashTable) {
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
    public Integer get(String key) {
        int hash = calcHash(key);
        if (hashTable[hash % hashTable.length] != null) {
            Node currNode = hashTable[hash % hashTable.length];
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
    public Integer put(String key, Integer value) {
        int hash = calcHash(key);
        Node newNode = new Node(hash, key, value);
        if (hashTable[hash % hashTable.length] == null) {
            hashTable[hash % hashTable.length] = newNode;
            ++size;
        } else {
            Node currNode = hashTable[hash % hashTable.length];
            do {
                if (Objects.equals(currNode.getKey(), key)) {
                    Integer prevValue = currNode.setValue(value);
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

    private Node[] resize() {
        Node[] oldBaskets = hashTable;
        currCapacity *= 2;
        Node[] newBaskets = new Node[currCapacity];

        for (Node oldBasket : oldBaskets) {
            if (oldBasket != null) {
                Node currNode = oldBasket;
                do {
                    if (newBaskets[currNode.getHash() % newBaskets.length] == null) {
                        newBaskets[currNode.getHash() % newBaskets.length] = new Node(currNode.hash, currNode.key, currNode.value);
                    } else {
                        Node nodeToComplete = newBaskets[currNode.getHash() % newBaskets.length];
                        do {
                            if (nodeToComplete.next == null) {
                                nodeToComplete.next = new Node(currNode.hash, currNode.key, currNode.value);
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
    public Integer remove(String key) {
        int hash = calcHash(key);
        if (hashTable[hash % hashTable.length] != null) {
            Node currNode = hashTable[hash % hashTable.length];
            Node prevNode = null;
            do {
                if (Objects.equals(currNode.getKey(), key)) {
                    Integer removedValue = currNode.value;
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
    public void putAll(Map map) {
        if (map instanceof RBook) {
            RBook map2 = (RBook) map;
            for (Node node : map2.hashTable) {
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

    private static int calcHash(String key) {
        int hash = 0;
        if (key != null) {
            int keyLength = key.length();
            for (char digit : key.toCharArray()) {
                hash += (int) digit * keyLength--; // -- здесь специально для уменьшения
                // keyLength после использования
            }
        }
        return hash;
    }

//    public Iterator<String> iterator() {
//        return new Iterator<>() {
//            int countArray = 0;
//            int valuesCounter = 0;
//            Iterator<Node> subIterator = null;
//
//            public boolean hasNext() {
//                if (valuesCounter == size) {
//                    return false;
//                }
//                if (subIterator == null || !subIterator.hasNext()) {
//                    if (moveToNextCell()) {
//                        subIterator = hashTable[countArray].getNodes().iterator();
//                    } else {
//                        return false;
//                    }
//                }
//                return subIterator.hasNext();
//            }
//
//            private boolean moveToNextCell() {
//                ++countArray;
//                while (hashTable[countArray] == null) {
//                    ++countArray;
//                }
//                return hashTable[countArray] != null;
//            }
//
//            public V next() {
//                ++valuesCounter;
//                return (V) subIterator.next().getValue();
//            }
//        };
//    }
}
