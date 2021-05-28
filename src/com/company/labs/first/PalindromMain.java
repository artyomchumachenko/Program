package com.company.labs.first;

import java.util.Scanner;

public class PalindromMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder resultString = new StringBuilder(); // Результирующая строка
        String regexForSymbols = new String("[!;:()-_=+?.,]"); // Регулярка для удаления символов из строки
        int step = 0;
        int flag = 0;
        System.out.println("Введите строку: ");
        String inputString = scanner.nextLine();
        scanner.close();
        String copyString = inputString; // Копия исходной строки для инвертирования
        copyString = copyString.replaceAll(regexForSymbols, ""); // Удаление символов из копии исх. строки
        String[] copyStringArray = copyString.split(" "); // Массив слов из копии исх. строки
        char[] massiveSymbols = new char[copyString.length()]; // Создание массива символов для инвертирования исх. строки
        char[] stringToArray = copyString.toCharArray(); // Создание массива символов исх. строки
        // Запись инвертированного массива символов
        for (int i = stringToArray.length - 1; i >= 0; i--) {
            massiveSymbols[step] = stringToArray[i];
            step++;
        }
        String invertString = new String(massiveSymbols); // Создание инвертированной исх. строки
        String[] invertStringArray = invertString.split(" "); // Создание массива слов инвертированной исх. строки
        int invertStep = copyStringArray.length; // переменная, чтобы выводить легче палиндромы
        // Проверка слов палиндромов и запись "слово - его палиндром\n" в результирующую строку
        for (int i = 0; i < copyStringArray.length; i++) {
            for (int j = 0; j < copyStringArray.length; j++) {
                if ((copyStringArray[i].toLowerCase()).equals(invertStringArray[j].toLowerCase())) {
                    if (i != invertStep - j - 1) {
                        resultString.append(copyStringArray[i].toLowerCase())
                                    .append(" ")
                                    .append(invertStringArray[invertStep - j - 1].toLowerCase())
                                    .append("\n");
                    } else {
                        resultString.append(copyStringArray[i].toLowerCase())
                                    .append("\n");
                    }
                    flag++;
                    break;
                }
            }
        }
        // Флаг будет больше нуля, если мы нашли хотя бы одно слово палиндром
        if (flag == 0) {
            System.out.println();
            System.out.println("Искомых слов не найдено");
        } else {
            System.out.println();
            System.out.println(resultString.toString());
        }
    }
}
