package com.company.dz.five.factorial;

import java.util.Scanner;

public class FactorialMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number = 0;
        int factNumber = 0;
        int sum = 0;
        int plus = 0;
        boolean flag = true;

        System.out.println("Введите число k");
        do {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine();
                if (number > 0) {
                    flag = false;
                } else {
                    flag = true;
                    System.out.println("Повторите попытку");
                }
            } else {
                scanner.nextLine();
                System.out.println("Повторите попытку");
                flag = true;
            }
        } while (flag);

        do {
            if (sum != number) {
                if (sum > number) {
                    flag = false;
                    factNumber = 0;
                } else {
                    ++plus;
                    sum += plus;
                    flag = true;
                }
            } else {
                factNumber = plus;
                flag = false;
            }
        } while (flag);
        if (factNumber == 0) {
            System.out.println("false");
        } else {
            System.out.println("true\n" + factNumber);
        }
    }
}
