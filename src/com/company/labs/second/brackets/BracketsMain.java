package com.company.labs.second.brackets;

import java.util.Scanner;

public class BracketsMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString;
        char[] inputStringCharacters;
        int firstCharacters = 0;
        int secondCharacters = 0;

        System.out.println("Введите строку:");
        inputString = scanner.nextLine();
        inputStringCharacters = inputString.toCharArray();
        for (int i = 0; i < inputString.length(); i++) {
            if (inputStringCharacters[i] == '(') {
                ++firstCharacters;
            } else if (inputStringCharacters[i] == ')') {
                ++secondCharacters;
            }
        }
        if (firstCharacters == secondCharacters) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
