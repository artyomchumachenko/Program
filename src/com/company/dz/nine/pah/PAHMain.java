package com.company.dz.nine.pah;

import java.util.Scanner;
import java.util.Arrays;

public class PAHMain extends SizeSearch {
    public static void main(String[] args) {
        final int numberOfLines = 5;
        final int numberOfColumns = 5;
        boolean flag = true;
        Integer[][] array = new Integer[numberOfLines][numberOfColumns];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите элементы для двумерного массива " + numberOfColumns + "x" + numberOfLines);
        for (int i = 0; i < numberOfLines; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                do {
                    System.out.print(i + " " + j + ": ");
                    array[i][j] = scanner.nextInt();
                    flag = array[i][j] != 1 && array[i][j] != 0;
                } while (flag);
            }
        }
        int[] result = sizeSearch(array);
        System.out.println(Arrays.toString(result));
    }
}
