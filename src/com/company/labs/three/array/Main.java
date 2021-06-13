package com.company.labs.three.array;

public class Main {
    public static void main(String[] args) {
        SimpleArray<String> arraySize = new SimpleArray<>();
        arraySize.add("Hello World");
        arraySize.add("I'm Artyom");
        arraySize.add("I'm rly crazy fish");
        arraySize.add("Andrey");
        arraySize.add("Ivan");
        arraySize.add("Sofya");
        for (String obj : arraySize) {
            System.out.println(obj);
        }
    }
}
