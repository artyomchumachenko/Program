package com.company.lesson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

public class Main {

    private static void task2() {
        List<String> words = new ArrayList<>();

        words.add("Hello");
        words.add("world");

        List<String> words2 = new ArrayList<>();
        words2.add("Apple");
        words2.add("banana");

        List<String> plusWords = new ArrayList<>();
//        int i = 0;
//        for (String word : words) {
//            plusWords.add(word + words2.get(i));
//            ++i;
//        }

        for (int i = 0; i < words.size(); i++) {
            plusWords.add(words.get(i) + words2.get(i));
        }

        for (String word : plusWords) {
            System.out.println(word);
        }
//        for (String word : words) { // words2?
//            System.out.println(word);
//        }
//
//        System.out.println();
//        words.addAll(1, words2);
//
//        for (String word : words) {
//            System.out.println(word);
//        }
//
//        System.out.println();
//        words.add(words.size(), "hi");
//
//        for (String word : words) {
//            System.out.println(word);
//        }
//
//        System.out.println();
//        System.out.println(words.get(1));
    }

    public static void main(String[] args) {
        Collection<String> words = new ArrayList<>();
        Collection<String> copyWords = new ArrayList<>();

        words.add("Hello words!");
        words.add("Update coll words");
        copyWords.addAll(words);
        copyWords.add("Hello copyWords!");

        System.out.println("Print words: ");
        for (String word : words) {
            System.out.println(word);
        }
        System.out.println();
        System.out.println("Print copyWords: ");
        for (String word : copyWords) {
            System.out.println(word);
        }
        System.out.println();

        task2();
    }
}
