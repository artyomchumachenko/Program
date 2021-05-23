package com.company.labs.three.arraystr;

public class Main {
    public static void main(String[] args) {
        SimpleArray arraySize = new SimpleArray(12);
        arraySize.add("Hello World");
        arraySize.add("I'm Artyom");
        arraySize.add("I'm rly crazy fish");
        System.out.println("empty = " + arraySize.isEmpty());
        System.out.println("size = " + arraySize.size());
        System.out.println("contains with Hello World = " + arraySize.contains("Hello World"));
        System.out.println("get 1 index = " + arraySize.get(1));
        arraySize.set(0, "Bye Bye World");
        System.out.println("set 0 Bye Bye World = " + arraySize.get(0));
        arraySize.add(1, "I'm rly good program");
        System.out.println("get 1 = " + arraySize.get(1));
        arraySize.remove("I'm rly good program");
        arraySize.remove(0);
        arraySize.add("Versus Battle");
        System.out.println("bbw indexOf = " + arraySize.indexOf("Bye Bye World"));
        System.out.println("VB indexOf = " + arraySize.indexOf("Versus Battle"));
        System.out.println("SubList 0-2 = " + arraySize.subList(0, 2));
        arraySize.clear();
        System.out.println("empty = " + arraySize.isEmpty());
    }
}
