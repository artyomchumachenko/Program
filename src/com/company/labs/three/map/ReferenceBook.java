package com.company.labs.three.map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ReferenceBook implements Map {
    private Node[] hashTable;
    private int size = 0;
    private float threshold;

    public ReferenceBook() {
        hashTable = new Node[16];
        threshold = hashTable.length * 0.75f;
    }

//    public void add(Integer key, String value) {
//        Node<Integer, String> newNode = new Node<>(key, value);
//
//    }

    private int hash(Node<Integer, String> node) {
        return node.hashCode() % hashTable.length;
    }

    private int hash(final Integer key) {
        int hash = 37;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(java.lang.String key) {
        return false;
    }

    @Override
    public boolean containsValue(java.lang.Integer value) {
        return false;
    }

    @Override
    public java.lang.Integer get(java.lang.String key) {
        return null;
    }

    @Override
    public java.lang.Integer put(java.lang.String key, java.lang.Integer value) {
        Node newNode = new Node(key, value);
        int index = newNode.hash();

    }

    @Override
    public java.lang.Integer remove(java.lang.String key) {
        return null;
    }

    @Override
    public void putAll(Map map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<java.lang.String> iterator() {
        return new Iterator<java.lang.String>() {
            int countArray = 0;
            int valuesCounter = 0;
            Iterator<Node<Integer, String>> subIterator = null;

            @Override
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

            @Override
            public java.lang.String next() {
                ++valuesCounter;
                return (java.lang.String) subIterator.next().getValue();
            }
        };
    }

    public class Node<Integer, String> {
        private List<Node<Integer, String>> nodes;
        private int hash;
        private Integer key;
        private String value;

        private Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<Integer, String>>();
        }

        private List<Node<Integer, String>> getNodes() {
            return nodes;
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        private Integer getKey() {
            return key;
        }

        private String getValue() {
            return value;
        }

        private void setValue(String Value) {
            this.value = value;
        }

        public int hashCode() {
            hash = 37;
            hash = hash * 17 + key.hashCode();
            hash = hash * 17 + value.hashCode();
            return hash;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                Node<Integer, String> node = (Node) obj;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }
            return false;
        }
    }
}
