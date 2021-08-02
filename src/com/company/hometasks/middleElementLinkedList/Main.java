package com.company.hometasks.middleElementLinkedList;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LinkedList<String> array = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вводите данные, чтобы закончить введите [end]");
        String enter = scanner.nextLine();
        while (!enter.equalsIgnoreCase("end")) {
            array.add(enter);
            enter = scanner.nextLine();
        }

        System.out.println("Определение среднего элемента LinkedList");
        int second = array.size();
        int first = -1;
        for (String obj : array) {
            first++;
            first++;
            if (first == second) {
                System.out.println(obj);
                break;
            } else if (first > second) {
                System.out.println("Среднего элемента нет");
                break;
            }
        }

        System.out.println("Определение i-ого элемента с конца списка");
        System.out.println("Введите какой элемент с конца нужно вывести");
        first = scanner.nextInt();
        scanner.nextLine();
        int currElem = 0;
        final int ONE = 1;
        for (String obj : array) {
            currElem++;
            if (currElem == second - first + ONE) {
                System.out.println(obj);
                break;
            }
        }
    }
}
