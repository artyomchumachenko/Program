package com.company.labs.first;

import java.util.Scanner;

public class SortStringMain {
    public static void main(String[] args) {
        // Ввод n строк и заполнение массива строк
        int numberStrings = 0;
        boolean errorCode = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите кол-во строк: ");
            if (scanner.hasNextInt()) {
                numberStrings = scanner.nextInt();
                errorCode = false;
            } else {
                System.out.println("Повторите попытку, кол-во строк должно быть целым числом");
                errorCode = true;
                scanner.nextLine(); // fflush
            }
        } while (errorCode);
        String[] strings = new String[numberStrings];
        scanner.nextLine(); // fflush
        for (int i = 0; i < numberStrings; i++) {
            strings[i] = scanner.nextLine();
        }
        scanner.close();
        // Сортировка массива строк
        int flag = 0;
        int check = 0;
        do {
            check = 0;
            for (int i = 0; i < numberStrings - 1; i++) {
                if (strings[i].length() > strings[i + 1].length()) {
                    check++;
                    String buffer = strings[i];
                    strings[i] = strings[i + 1];
                    strings[i + 1] = buffer;
                }
                if (strings[i].length() == strings[i + 1].length()) {
                    flag = strings[i + 1].compareTo(strings[i]);
                    if (flag < 0) {
                        check++;
                        String buffer = strings[i];
                        strings[i] = strings[i + 1];
                        strings[i + 1] = buffer;
                    }
                }
            }
        } while (check > 0);
        // Вывод упорядоченного массива строк
        System.out.println("");
        for (int i = 0; i < numberStrings; i++) {
            System.out.println(strings[i]);
        }
    }
}
