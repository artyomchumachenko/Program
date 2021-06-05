package com.company.dz.ten.sneak;

import java.util.Scanner;

public class SneakMain {
    private final static String ERROR_NOT_INTEGER = "Это не целое число, повторите попытку";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flagForMatrixSize;
        int sizeForMatrix = 0;

        System.out.println("Введите целое число n: ");
        do {
            if (scanner.hasNextInt()) {
                sizeForMatrix = scanner.nextInt();
                scanner.nextLine();
                flagForMatrixSize = false;
            } else {
                scanner.nextLine();
                System.out.println(ERROR_NOT_INTEGER);
                flagForMatrixSize = true;
            }
        } while (flagForMatrixSize);
        scanner.close();

        System.out.println(printTable(sizeForMatrix));
    }

    public static String printTable(int n) {
        StringBuilder resultTable = new StringBuilder();
        int maxNumber = n * n;

        if (maxNumber == 1) {
            resultTable.append("1");
            return resultTable.toString();
        }

        int x = 0;
        int y = 0;
        int controlX = 0;
        int controlY = 1;
        int[][] table = new int[n][n];

        for (int i = 1; i <= maxNumber; i++) {
            table[x][y] = i;
            if (((y + controlY) >= n) || ((y + controlY) < 0) || ((x + controlX) >= n) ||
                    ((x + controlX) < 0) || (table[x + controlX][y + controlY] != 0)) {
                int temp = controlY;
                controlY = -controlX;
                controlX = temp;
            }
            x += controlX;
            y += controlY;
        }
        for (int i = 0; i < n; i++) {
            resultTable.append("\n");
            for (int j = 0; j < n; j++) {
                resultTable.append(table[i][j]).append("\t");
            }
        }
        return resultTable.toString();
    }
}
