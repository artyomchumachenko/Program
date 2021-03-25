package com.company.dz.four.maxareatriangle;

import java.util.Scanner;
import java.lang.Math;

public class LargestAreaTriangleMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = 0;
        int[] x;
        int[] y;
        boolean flag;
        double maxArea = 0;
        double currArea = 0;
        double halfPerimeter = 0;
        double[] length = new double[3];

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

        for (int i = 0; i < numberOfPoints; i++) {
            if (i == numberOfPoints - 2) {
                length[0] = Math.sqrt(Math.pow(x[i + 1] - x[i], 2) + Math.pow(y[i + 1] - y[i], 2));
                length[1] = Math.sqrt(Math.pow(x[0] - x[i + 1], 2) + Math.pow(y[0] - y[i + 1], 2));
                length[2] = Math.sqrt(Math.pow(x[i] - x[0], 2) + Math.pow(y[i] - y[0], 2));
            } else if (i == numberOfPoints - 1) {
                length[0] = Math.sqrt(Math.pow(x[0] - x[i], 2) + Math.pow(y[0] - y[i], 2));
                length[1] = Math.sqrt(Math.pow(x[1] - x[0], 2) + Math.pow(y[1] - y[0], 2));
                length[2] = Math.sqrt(Math.pow(x[i] - x[1], 2) + Math.pow(y[i] - y[1], 2));
            } else {
                length[0] = Math.sqrt(Math.pow(x[i + 1] - x[i], 2) + Math.pow(y[i + 1] - y[i], 2));
                length[1] = Math.sqrt(Math.pow(x[i + 2] - x[i + 1], 2) + Math.pow(y[i + 2] - y[i + 1], 2));
                length[2] = Math.sqrt(Math.pow(x[i] - x[i + 2], 2) + Math.pow(y[i] - y[i + 2], 2));
            }
            halfPerimeter = (length[0] + length[1] + length[2]) / 2;
            currArea = Math.sqrt(halfPerimeter *
                    (halfPerimeter - length[0]) *
                    (halfPerimeter - length[1]) *
                    (halfPerimeter - length[2]));
            if (currArea > maxArea) {
                maxArea = currArea;
            }
            length[0] = 0;
            length[1] = 0;
            length[2] = 0;
            currArea = 0;
        }
        System.out.printf("%.2f", maxArea);
    }
}
