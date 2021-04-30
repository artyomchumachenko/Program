package com.company.labs.three;

import java.util.Scanner;

import com.company.labs.three.array.SimpleArray;
import com.company.labs.three.linked.SimpleLinked;
import com.company.labs.three.map.RBook.Node;
import com.company.labs.three.map.RBook;

public class Main {
    private static final String OUTPUT_ALL_ITEMS_FLAG = "Вывести все элементы?";
    private static final int EMPTY = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleArray<String> arrayList = new SimpleArray<>();
        SimpleLinked linkedList = new SimpleLinked();
        RBook<String, String> map = new RBook<>();
        String input;
        String collection;

        do {
            collection = scanner.nextLine();
            if (!collection.matches("^ARRAY$|^LINKED$|^MAP$")) {
                System.out.println("Повторите попытку");
            }
        } while (!collection.matches("^ARRAY$|^LINKED$|^MAP$"));

        do {
            System.out.println("Введите элемент коллекции: ");
            input = scanner.nextLine();
            if (input.equals("END")) {
                break;
            }
            switch (collection) {
                case "ARRAY":
                    arrayList.add(input);
                    break;
                case "LINKED":
                    linkedList.add(input);
                    break;
                case "MAP":
                    String value = null;
                    value = scanner.nextLine();
                    map.put(input, value);
                    break;
            }
        } while (!input.equals("END"));
        System.out.print("Количество элементов в структуре: ");
        switch (collection) {
            case "ARRAY":
                System.out.println(arrayList.size());
                System.out.println(OUTPUT_ALL_ITEMS_FLAG);
                if (scanner.nextLine().equals("YES")) {
                    for (String element : arrayList) {
                        System.out.println(element);
                    }
                }
                break;
            case "LINKED":
                System.out.println(linkedList.size());
                System.out.println(OUTPUT_ALL_ITEMS_FLAG);
                if (scanner.nextLine().equals("YES")) {
                    for (String element : linkedList.toArray()) {
                        System.out.println(element);
                    }
                }
                break;
            case "MAP":
                System.out.println(map.size());
                System.out.println(OUTPUT_ALL_ITEMS_FLAG);
                if (scanner.nextLine().equals("YES")) {
                    for (Node node : map.hashTable) {
                        if (node != null) {
                            System.out.println(node.getKey() + "->" + node.getValue());
                        }
                    }
                }
                break;
        }
        long time = EMPTY;
        System.out.println("Вывести элемент по индексу или ключу?");
        if (scanner.nextLine().equals("YES")) {
            int index = EMPTY;
            time = System.currentTimeMillis();
            switch (collection) {
                case "ARRAY":
                    boolean flag;
                    do {
                        System.out.print("Введите индекс: ");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                            flag = true;
                            if (index <= 0) {
                                System.out.println("Повторите попытку");
                                flag = false;
                            }
                        } else {
                            scanner.nextLine();
                            System.out.println("Повторите попытку");
                            flag = false;
                        }
                    } while (!flag);
                    System.out.println(arrayList.get(index));
                    break;
                case "LINKED":
                    do {
                        System.out.print("Введите индекс: ");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                            flag = true;
                            if (index <= 0) {
                                System.out.println("Повторите попытку");
                                flag = false;
                            }
                        } else {
                            scanner.nextLine();
                            System.out.println("Повторите попытку");
                            flag = false;
                        }
                    } while (!flag);
                    index = scanner.nextInt();
                    System.out.println(linkedList.get(index));
                    break;
                case "MAP":
                    System.out.println("Введите ключ: ");
                    String key = scanner.nextLine();
                    System.out.println(map.get(key));
                    break;
            }
        }
        scanner.close();
        if (time != EMPTY) {
            double currTime = System.currentTimeMillis();
            System.out.println("Поиск занял " + (currTime - time) + " миллисекунд.");
        }
    }
}