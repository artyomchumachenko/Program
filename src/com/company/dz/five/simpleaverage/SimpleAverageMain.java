package com.company.dz.five.simpleaverage;

import java.util.Scanner;

public class SimpleAverageMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfNumbers = 0;
        double tempNumber = 0;
        double sumOrder = 0;
        final double DEFAULT_ORDER = 1;
        double simpleAverage = 0;
        double sumOfNumbers = 0;
        boolean flag = true;
        String regexFirst = "^(x:\\s-?[0-9]*[.,]?[0-9]+;\\sp:\\s-?[0-9]*[.,]?[0-9]+)$";
        String regexSecond = "^(p:\\s-?[0-9]*[.,]?[0-9]+;\\sx:\\s-?[0-9]*[.,]?[0-9]+)$";
        String regexThree = "^(x:\\s-?[0-9]*[.,]?[0-9]+)$";
        String inputString;

        System.out.println("Введите количество значений");
        do {
            if (scanner.hasNextInt()) {
                numberOfNumbers = scanner.nextInt();
                scanner.nextLine();
                if (numberOfNumbers > 0) {
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
        for (int currStep = 0; currStep < numberOfNumbers; currStep++) {
            System.out.println("Введите " + (currStep + 1) + " значение");
            do {
                inputString = scanner.nextLine();
                if (inputString.matches(regexFirst)) {
                    String[] parts = inputString.split(";");
                    String numberValue = parts[0];
                    String orderValue = parts[1];
                    numberValue = numberValue.replace("x: ", "");
                    double number = Double.parseDouble(numberValue);
                    orderValue = orderValue.replace(" p: ", "");
                    double order = Double.parseDouble(orderValue);
                    tempNumber = number * order;
                    sumOfNumbers += tempNumber;
                    sumOrder += order;
                    flag = false;
                } else if (inputString.matches(regexSecond)) {
                    String[] parts = inputString.split(";");
                    String numberValue = parts[1];
                    String orderValue = parts[0];
                    numberValue = numberValue.replace(" x: ", "");
                    double number = Double.parseDouble(numberValue);
                    orderValue = orderValue.replace("p: ", "");
                    double order = Double.parseDouble(orderValue);
                    tempNumber = number * order;
                    sumOfNumbers += tempNumber;
                    sumOrder += order;
                    flag = false;
                } else if (inputString.matches(regexThree)) {
                    String numberValue = inputString;
                    numberValue = numberValue.replace("x: ", "");
                    double number = Double.parseDouble(numberValue);
                    tempNumber = number * DEFAULT_ORDER;
                    sumOfNumbers += tempNumber;
                    sumOrder += DEFAULT_ORDER;
                    flag = false;
                } else {
                    System.out.println("Повторите попытку");
                    flag = true;
                }
            } while (flag);
        }
        simpleAverage = sumOfNumbers / sumOrder;
        System.out.println(String.format("%.3f", simpleAverage));
    }
}
