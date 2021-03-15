package com.company.dz.three.students;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NUMBER_OF_SCORES = 5;
        String name;
        String group;
        int numberOfStudents = 0;
        boolean numberFlag = true;

        do {
            System.out.println("Введите количество студентов: ");
            if (scanner.hasNextInt()) {
                numberOfStudents = scanner.nextInt();
                if (numberOfStudents <= 0) {
                    System.out.println("Студентов не может быть меньше 1");
                    numberFlag = true;
                } else {
                    numberFlag = false;
                }
            } else {
                scanner.nextLine();
                System.out.println("Повторите попытку");
                numberFlag = true;
            }
        } while (numberFlag);
        Student[] students = new Student[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            int[] scores = new int[NUMBER_OF_SCORES];
            System.out.println("Введите фамилию и инициалы " + (i + 1) + " студента: ");
            scanner.nextLine();
            name = scanner.nextLine();
            System.out.println("Введите номер группы " + (i + 1) + " студента: ");
            group = scanner.nextLine();
            System.out.println("Введите оценки для " + (i + 1) + " студента: ");
            for (int j = 0; j < NUMBER_OF_SCORES; j++) {
                do {
                    if (scanner.hasNextInt()) {
                        scores[j] = scanner.nextInt();
                        if (scores[j] < 0 || scores[j] > 10) {
                            System.out.println("Повторите попытку, 0 <= оценка <= 10");
                        }
                    } else {
                        scanner.nextLine();
                        System.out.println("Повторите попытку");
                    }
                } while (scores[j] < 0 || scores[j] > 10);
            }
            students[i] = new Student(name, group, scores);
        }
        scanner.close();
        System.out.println("Список студентов: ");
        for (Student student : students) {
            student.printAll();
        }
        System.out.println("\nСписок студентов с высокой успеваемостью: ");
        for (Student student : students) {
            student.printHighScore();
        }
    }
}
