package com.company.dz.four;

import java.util.Scanner;
import java.lang.Math;

public class LargestAreaTriangleMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = 0;
        int[] x;
        int[] y;
        boolean flag = false;
        int[] lengthSide;

        System.out.println("Введите количество точек: ");
        do {
            if (scanner.hasNextInt()) {
                numberOfPoints = scanner.nextInt();
                if (numberOfPoints < 3) {
                    System.out.println("Повторите попытку, число должно быть >/= 3");
                    flag = true;
                } else flag = false;
            } else {
                System.out.println("Повторите попытку, введите целое число точек: ");
                flag = true;
            }
            scanner.nextLine(); // fflush
        } while (flag);

        x = new int[numberOfPoints];
        y = new int[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            System.out.println("x" + (i + 1) + ": ");
            x[i] = scanner.nextInt();
            scanner.nextLine();
            System.out.println("y" + (i + 1) + ": ");
            y[i] = scanner.nextInt();
            scanner.nextLine();
        }

    }
}
