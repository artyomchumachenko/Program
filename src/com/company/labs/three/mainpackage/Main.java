package com.company.labs.three.mainpackage;

import com.company.labs.three.arraystr.SimpleArray;
import com.company.labs.three.linkedstr.SimpleLinked;
import com.company.labs.three.mapstr.RBook;

import java.util.Scanner;

public class Main {
    private static final String OUTPUT_ALL_ITEMS_FLAG = "Вывести все элементы?";
    private static final int EMPTY = 0;
    private static final String REGEX_FOR_LISTS = "^ARRAY$|^LINKED$|^MAP$";
    private static final String CHOOSE_ARRAY = "ARRAY";
    private static final String CHOOSE_LINKED = "LINKED";
    private static final String CHOOSE_MAP = "MAP";
    private static final String CHOOSE_YES = "YES";
    private static final String CHOOSE_END = "END";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleArray arrayList = new SimpleArray();
        SimpleLinked linkedList = new SimpleLinked();
        RBook map = new RBook();
        String input;
        String collection;

        do {
            collection = scanner.nextLine().toUpperCase();
            if (!collection.matches(REGEX_FOR_LISTS)) {
                System.out.println("Повторите попытку");
            }
        } while (!collection.matches(REGEX_FOR_LISTS));

        do {
            System.out.println("Введите элемент коллекции: ");
            input = scanner.nextLine();
            if (input.toUpperCase().equals(CHOOSE_END)) {
                break;
            }
            switch (collection) {
                case CHOOSE_ARRAY -> arrayList.add(input);
                case CHOOSE_LINKED -> linkedList.add(input);
                case CHOOSE_MAP -> {
                    boolean flag = true;
                    int value = EMPTY;
                    do {
                        System.out.print("Введите значение: ");
                        if (scanner.hasNextInt()) {
                            value = scanner.nextInt();
                            flag = false;
                        } else {
                            scanner.nextLine();
                            System.out.println("Повторите попытку");
                            flag = true;
                        }
                    } while (flag);
                    scanner.nextLine();
                    map.put(input, value);
                }
            }
        } while (true);
        System.out.print("Количество элементов в структуре: ");
        switch (collection) {
            case CHOOSE_ARRAY -> {
                System.out.println(arrayList.size());
                System.out.println(OUTPUT_ALL_ITEMS_FLAG);
                if (scanner.nextLine().toUpperCase().equals(CHOOSE_YES)) {
                    for (String element : arrayList.toArray()) {
                        System.out.println(element);
                    }
                }
            }
            case CHOOSE_LINKED -> {
                System.out.println(linkedList.size());
                System.out.println(OUTPUT_ALL_ITEMS_FLAG);
                if (scanner.nextLine().toUpperCase().equals(CHOOSE_YES)) {
                    for (String element : linkedList.toArray()) {
                        System.out.println(element);
                    }
                }
            }
            case CHOOSE_MAP -> {
                System.out.println(map.size());
                System.out.println(OUTPUT_ALL_ITEMS_FLAG);
                if (scanner.nextLine().toUpperCase().equals(CHOOSE_YES)) {
                    String[] temp = map.getAllKeys();
                    for (String string : temp) {
                        System.out.println(string + " -> " + map.get(string));
                    }
                }
            }
        }
        long time = EMPTY;
        System.out.println("Вывести элемент по индексу или ключу?");
        if (scanner.nextLine().toUpperCase().equals(CHOOSE_YES)) {
            int index = EMPTY;
            time = System.currentTimeMillis();
            switch (collection) {
                case CHOOSE_ARRAY:
                    boolean flag;
                    do {
                        System.out.print("Введите индекс: ");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                            flag = true;
                            if (index <= 0) {
                                scanner.nextLine();
                                System.out.println("Повторите попытку");
                                flag = false;
                            }
                        } else {
                            scanner.nextLine();
                            System.out.println("Повторите попытку");
                            flag = false;
                        }
                    } while (!flag);
                    scanner.nextLine();
                    System.out.println(arrayList.get(index));
                    break;
                case CHOOSE_LINKED:
                    do {
                        System.out.print("Введите индекс: ");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                            flag = true;
                            if (index <= 0) {
                                scanner.nextLine();
                                System.out.println("Повторите попытку");
                                flag = false;
                            }
                        } else {
                            scanner.nextLine();
                            System.out.println("Повторите попытку");
                            flag = false;
                        }
                    } while (!flag);
                    scanner.nextLine();
                    System.out.println(linkedList.get(index));
                    break;
                case CHOOSE_MAP:
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
