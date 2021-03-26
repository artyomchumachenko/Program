package com.company.dz.four.mindistpoint;

import java.util.Scanner;

public class CalcForPointMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfPoints = 0;
        double minSumDist = 0;
        double currSumDist = 0;
        Point[] points;
        Point minPoint;

        System.out.println("Введите количество точек: ");
        numberOfPoints = scanner.nextInt();
        scanner.nextLine(); // fflush
        points = new Point[numberOfPoints];
//        points[0] = new Point();

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
            int z = Integer.parseInt(splitString[2]);
            points[i] = new Point(x, y, z);
        }
        scanner.close();
        minPoint = points[0];
        if (numberOfPoints > 1) {
            for (int i = 0; i < numberOfPoints; i++) {
                minSumDist += Point.distCalculation(points[0], points[i]);
            }
        } else {
            System.out.println("Points < 1");
        }
        for (int i = 1; i < numberOfPoints; i++) {
            for (int j = 0; j < numberOfPoints; j++) {
                if (i != j) {
                    currSumDist += Point.distCalculation(points[i], points[j]);
                }
            }
            if (minSumDist > currSumDist) {
                minSumDist = currSumDist;
                minPoint = points[i];
            }
        }
        System.out.println(minPoint.getAll() + String.format(" %.2f", minSumDist));
    }
}
