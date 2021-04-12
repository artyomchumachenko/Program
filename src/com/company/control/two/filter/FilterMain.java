package com.company.control.two.filter;

import java.util.Scanner;

public class FilterMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите исходную строку");
        String inputString = scanner.nextLine();
        StringBuilder newString = new StringBuilder();
        String[] splitString = inputString.split(" ");
        if (splitString[0].equals("S")) {
            for (int i = 1; i < splitString.length; i++) {
                if (splitString[i].charAt(0) != 'i' && splitString[i].charAt(0) != 'I') {
                    newString.append(splitString[i]).append(" ");
                }
            }
        } else {
            for (int i = 1; i < splitString.length; i++) {
                if (Integer.valueOf(splitString[i]) <= 10) {
                    newString.append(splitString[i]).append(" ");
                }
            }
        }
        System.out.println(newString.toString());
    }
}
