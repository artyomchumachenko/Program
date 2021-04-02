package com.company.dz.five.inflation;

import java.util.Scanner;

public class InflationMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double initPrice = 0;
        double initInflation = 0;
        int numberOfMonths = 0;
        double[] difference;
        boolean flag = true;
        double currPrice = 0;
        double currInflation = 0;
        final double ONE_HUNDRED = 100;

        System.out.println("Введите изначальную цену товара на начало месяца с учетом изначального"
                + " процента инфляции");
        do {
            if (scanner.hasNextDouble()) {
                initPrice = scanner.nextDouble();
                if (initPrice > 0) {
                    flag = false;
                } else {
                    System.out.println("Некорректный ввод, повторите попытку");
                    flag = true;
                }
            } else {
                System.out.println("Некорректный ввод, повторите попытку");
                scanner.nextLine();
                flag = true;
            }
        } while (flag);
        System.out.println("Введите изначальный процент инфляции");
        do {
            if (scanner.hasNextDouble()) {
                initInflation = scanner.nextDouble();
                flag = false;
            } else {
                System.out.println("Некорректный ввод, повторите попытку");
                scanner.nextLine();
                flag = true;
            }
        } while (flag);
        System.out.println("Введите количество месяцев");
        do {
            if (scanner.hasNextInt()) {
                numberOfMonths = scanner.nextInt();
                scanner.nextLine();
                if (numberOfMonths > 0) {
                    flag = false;
                } else {
                    System.out.println("Некорректный ввод, повторите попытку");
                    flag = true;
                }
            } else {
                System.out.println("Некорректный ввод, повторите попытку");
                scanner.nextLine();
                flag = true;
            }
        } while (flag);
        difference = new double[numberOfMonths];
        System.out.println("Введите числа, которые является разницей между процентом инфляции k-го"
                + " месяца и предыдущего (k-1) месяца");
        for (int currStep = 0; currStep < numberOfMonths; currStep++) {
            System.out.println("Введите разницу " + (currStep + 1) + " и " + currStep + " месяца");
            do {
                if (scanner.hasNextDouble()) {
                    difference[currStep] = scanner.nextDouble();
                    flag = false;
                } else {
                    System.out.println("Некорректный ввод, повторите попытку");
                    scanner.nextLine();
                    flag = true;
                }
            } while (flag);
        }

        currInflation = initInflation;
        currPrice = initPrice;
        for (int currStep = 0; currStep < numberOfMonths; currStep++) {
            currInflation = difference[currStep] + currInflation;
            if (currInflation != 0) {
                currPrice = currPrice * ((ONE_HUNDRED + currInflation) / ONE_HUNDRED);
            }
            if (currPrice <= 0) {
                System.out.println("Обвал экономики, цена товара <= 0");
                break;
            }
        }
        if (currPrice > 0) {
            System.out.println(String.format("Цена товара = %.2f", currPrice));
        }
    }
}
