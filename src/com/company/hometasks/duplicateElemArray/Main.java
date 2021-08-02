package com.company.hometasks.duplicateElemArray;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[10]; // от 1 до 100
        Scanner scanner = new Scanner(System.in);

        int currScore = 1;
        int finishSum = 0;
        while (currScore != 11) {
            finishSum += currScore;
            currScore++;
        }

        int arraySum = 0;
        for (int i = 0; i < 10; i++) {
            array[i] = scanner.nextInt();
            arraySum += array[i];
            scanner.nextLine();
        }

        System.out.println(finishSum - arraySum);
    }
}
