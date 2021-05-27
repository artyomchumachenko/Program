package com.company.labs.three.linkedstr;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        SimpleLinked linkedList = new SimpleLinked();
//        linkedList.add("Hello World");
//        linkedList.add("I'm Artyom");
//        linkedList.add("I'm rly crazy fish");
//        System.out.println("empty = " + linkedList.isEmpty());
//        System.out.println("size = " + linkedList.size());
//        System.out.println("contains with Hello World = " + linkedList.contains("Hello World"));
//        System.out.println("get 1 index = " + linkedList.get(1));
//        linkedList.set(0, "Bye Bye World");
//        System.out.println("set 0 Bye Bye World = " + linkedList.get(0));
//        linkedList.add(1, "I'm rly good program");
//        System.out.println("get 1 = " + linkedList.get(1));
//        linkedList.remove("I'm rly good program");
//        linkedList.remove(0);
//        linkedList.add("Versus Battle");
//        System.out.println("bbw indexOf = " + linkedList.indexOf("Bye Bye World"));
//        System.out.println("VB indexOf = " + linkedList.indexOf("Versus Battle"));
//        System.out.println("SubList 0-2 = " + linkedList.subList(0, 2));
//        linkedList.clear();
//        System.out.println("empty = " + linkedList.isEmpty());

//        SimpleLinked linkedList = new SimpleLinked();
//        linkedList.add("a1");
//        linkedList.add("b2");
//        linkedList.add("c3");
//        linkedList.add("d4");
//        linkedList.add("e5");
//        linkedList.add("f6");
//        System.out.println(Arrays.asList(linkedList.toArray()));
//        System.out.println(Arrays.asList(linkedList.subList(1, 4).toArray()));

        SimpleLinked linkedList = new SimpleLinked();
        linkedList.add("a1");
        linkedList.add("b2");
        System.out.println(Arrays.asList(linkedList.toArray()));
        linkedList.remove(1);
        linkedList.add("c3");
        System.out.println(Arrays.asList(linkedList.toArray()));
    }
}
