package com.company.labs.first;

import java.util.Scanner;

public class DeleteSymbolsMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите исходную строчку: ");
        String text = scanner.nextLine();
        String regexForDelete = "[^а-яА-Яa-zA-Z]";
        String result = text.replaceAll(regexForDelete, " ");
        System.out.println("Результат работы программы: ");
        System.out.println(result);
        scanner.close();
    }
}
