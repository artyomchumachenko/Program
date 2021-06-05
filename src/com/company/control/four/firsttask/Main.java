package com.company.control.four.firsttask;

import java.util.Scanner;

public class Main {
    private final static String ERROR_NOT_INTEGER = "Повторите попытку, число не целое";
    private final static int ONE = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        int[][] matrix;
        boolean intFlag;
        int currSum = 0;

        System.out.print("Введите размер квадратной матрицы: ");
        do {
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                System.out.println("Размер принят");
                intFlag = false;
            } else {
                scanner.nextLine();
                System.out.println(ERROR_NOT_INTEGER);
                intFlag = true;
            }
        } while (intFlag);
        matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * (200 + 1)) - 100;
            }
        }

        System.out.println("Исходная матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%6d\t", matrix[i][j]);
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                currSum += matrix[i][j];
            }
            matrix[i][n - i - ONE] = currSum;
            matrix[i][i] = currSum;
            currSum = 0;
        }

        System.out.println("Результат:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%6d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
