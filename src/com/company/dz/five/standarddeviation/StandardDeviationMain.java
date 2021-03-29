package com.company.dz.five.standarddeviation;

import java.util.Scanner;

public class StandardDeviationMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfNumbers = 0;
        double[] number;
        double amount = 0;
        double[] squaresOfDifferences;
        double sumSquares = 0;
        double standardDeviation = 0;
        boolean flag = true;
        double simpleAverage = 0;

        System.out.println("Введите количество чисел");
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
        number = new double[numberOfNumbers];
        for (int currStep = 0; currStep < numberOfNumbers; currStep++) {
            System.out.println("Введите " + (currStep + 1) + " число");
            do {
                if (scanner.hasNextDouble()) {
                    number[currStep] = scanner.nextDouble();
                    amount += number[currStep];
                    flag = false;
                } else {
                    System.out.println("Некорректный ввод, повторите попытку");
                    scanner.nextLine();
                    flag = true;
                }
            } while (flag);
        }
        simpleAverage = amount / numberOfNumbers;
        squaresOfDifferences = new double[numberOfNumbers];
        for (int currStep = 0; currStep < numberOfNumbers; currStep++) {
            squaresOfDifferences[currStep] = Math.pow((number[currStep] - simpleAverage), 2);
            sumSquares += squaresOfDifferences[currStep];
        }
        standardDeviation = Math.sqrt((sumSquares) / (numberOfNumbers));
        System.out.println(String.format("%.3f", standardDeviation));
    }
}
