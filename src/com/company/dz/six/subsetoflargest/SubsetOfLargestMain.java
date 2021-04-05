package com.company.dz.six.subsetoflargest;

import java.util.*;

public class SubsetOfLargestMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Double> numbers = new TreeSet<>();
        int numberOfNumbers = 0;
        double simpleAverage = 0;
        boolean flag = true;
        do {
            System.out.println("Введите " + (numberOfNumbers + 1) + " число");
            if (scanner.hasNextDouble()) {
                double currNumber = scanner.nextDouble();
                if (currNumber < 0) {
                    System.out.println("Числа должны быть => 0");
                    flag = true;
                } else if (currNumber > 0) {
                    numbers.add(currNumber);
                    ++numberOfNumbers;
                    simpleAverage += currNumber;
                    flag = true;
                } else {
                    flag = false;
                }
            } else {
                flag = true;
                scanner.nextLine();
                System.out.println("Повторите попытку");
            }
        } while (flag);
        simpleAverage = simpleAverage / numberOfNumbers;
        for (Double number : numbers) {
            if (number > simpleAverage) {
                System.out.println(number);
            }
        }
    }
}
