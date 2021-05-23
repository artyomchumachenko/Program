package com.company.control.three.first;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<String> result = new ArrayList<>();
        final int numbers = 10;
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int nine = 9;
        final int ten = 10;
        do {
            input = scanner.nextLine();
            if (input.matches("")) {
                break;
            }
            input = input.replaceAll("[^0-9]", "");
            if (input.matches("^(8|\\+7|7)?\\d{10}$")) {
                char[] temp = input.toCharArray();
                if (temp.length == numbers) {
                    result.add("Correct:+7" + input);
                } else {
                    result.add("Correct:+7" + temp[one] + temp[two] + temp[three] + temp[four] + temp[five] + temp[six] + temp[seven]
                            + temp[eight] + temp[nine] + temp[ten]);
                }
            } else {
                result.add("No");
            }
        } while (true);
        for (String obj : result) {
            System.out.println(obj);
        }
    }
}
