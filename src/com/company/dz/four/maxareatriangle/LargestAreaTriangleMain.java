package com.company.dz.four.maxareatriangle;

import java.util.Scanner;
import java.lang.Math;

public class LargestAreaTriangleMain {

    public static double calcCurrArea(Point firstPoint, Point secondPoint, Point threePoint) {
        double halfPerimeter = 0;
        double[] length = new double[3];
        length[0] = Point.calcLength(firstPoint, secondPoint);
        length[1] = Point.calcLength(firstPoint, threePoint);
        length[2] = Point.calcLength(secondPoint, threePoint);
        halfPerimeter = (length[0] + length[1] + length[2]) / 2;
        return Math.sqrt(halfPerimeter *
                (halfPerimeter - length[0]) *
                (halfPerimeter - length[1]) *
                (halfPerimeter - length[2]));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = 0;
        boolean flag = true;
        Point[] points;
        double currArea = 0;
        double maxArea = 0;

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
        points = new Point[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            System.out.println("Введите координаты " + (i + 1) + " точки: ");
            String characterDetection;
            characterDetection = scanner.nextLine();
            characterDetection = characterDetection.replace("(", "")
                                                   .replace(")", "")
                                                   .replace(" ", "");
            String[] splitString = characterDetection.split("\\,");
            int x = Integer.parseInt(splitString[0]);
            int y = Integer.parseInt(splitString[1]);
            points[i] = new Point(x, y);
        }
        scanner.close();
        for (int i = 0; i < numberOfPoints; i++) {
            for (int j = 0; j < numberOfPoints; j++) {
                for (int k = 0; k < numberOfPoints; k++) {
                    currArea = calcCurrArea(points[i], points[j], points[k]);
                    if (currArea > maxArea) {
                        maxArea = currArea;
                    }
                }
            }
        }
        System.out.println(String.format("%.2f", maxArea));
    }
}
