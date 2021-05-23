package com.company.labs.three.array;

public class Main {
    public static void main(String[] args) {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("Testing Mode");
        array.add("Testing Krot");
        array.add("Testing Spot");
        System.out.println("Список:");
        for (String obj : array) {
            System.out.println(obj);
        }
        System.out.println();
        System.out.println("size = " + array.size());
        System.out.println("empty = " + array.isEmpty());
        System.out.println("contains Testing Mode = " + array.contains("Testing Mode"));
        System.out.println("contains Testing MDDD = " + array.contains("Testing MDDD"));
        SimpleArray<String> testArray = new SimpleArray<>();
        testArray.add("Test List One");
        testArray.add("Test List Two");
        testArray.add("Test List Three");
        testArray.add("Test List Four");
        testArray.addAll(array);
        testArray.addAll(1, array);
        System.out.println();
        for (String obj : testArray) {
            System.out.println(obj);
        }
        testArray.clear();
        for (String obj : testArray) {
            System.out.println(obj);
        }
        System.out.println();
        System.out.println("get index 1 array = " + array.get(1));
        array.add(1, "FirstIndex");
        for (String obj : testArray) {
            System.out.println(obj);
        }
        array.remove(1);
        array.remove("Testing Krot");
        for (String obj : testArray) {
            System.out.println(obj);
        }
        System.out.println();
        System.out.println("indexOf Testing Spot = " + array.indexOf("Testing Spot"));
        System.out.println("testArray 1-3 index subList = ");
        for (String obj : testArray.subList(1, 3)) {
            System.out.println(obj);
        }
        testArray.removeAll(array);
        for (String obj : testArray) {
            System.out.println(obj);
        }
    }
}
