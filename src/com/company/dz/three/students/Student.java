package com.company.dz.three.students;

import java.util.Arrays;

public class Student {
    private String name;
    private String group;
    private final int NUMBER_OF_SCORES = 5;
    private boolean flag = true;
    private int[] scores = new int[NUMBER_OF_SCORES];

    public Student(String name, String group, int[] scores) {
        this.name = name;
        this.group = group;
        this.scores = scores;
        for (int score : scores) {
            if (score < 9) {
                flag = false;
                break;
            }
        }
    }

    public void printAll() {
        System.out.println("ФИО: " + name + "\nГруппа: " + group + "\nУспеваемость: " + Arrays.toString(scores));
    }

    public void printHighScore() {
        if (flag) {
            System.out.println("ФИО: " + name + "\nГруппа: " + group + "\nУспеваемость: " + Arrays.toString(scores));
        }
    }
}
