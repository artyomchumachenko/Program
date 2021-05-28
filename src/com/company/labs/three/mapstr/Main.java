package com.company.labs.three.mapstr;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RBook myMap = new RBook();
        myMap.put("Hello World", 14);
        myMap.put("I'm Artyom", 20);
        myMap.put("I'm rly crazy fish", 30);
        System.out.println("empty = " + myMap.isEmpty());
        System.out.println("size = " + myMap.size());
        System.out.println("get Hello World index = " + myMap.get("Hello World"));
        myMap.put("Bye Bye World", 20);
        System.out.println("set 0 Bye Bye World = " + myMap.get("Bye Bye World"));
        myMap.put("I'm rly good program", 1);
        myMap.remove("I'm rly good program");
        myMap.clear();
        System.out.println("empty = " + myMap.isEmpty());
//        RBook myMap = new RBook(1);
//        myMap.put("test2", 1);
//        myMap.put("test4", 2);
//        myMap.put("test6", 3);
//        myMap.put("test8", 4);
    }
}
