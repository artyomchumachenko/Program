package com.company.control.two;

import java.util.Scanner;

public class FibonachiMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int currFirst = 0;
        int currSecond = 1;
        int currNum = 0;
        int first = 0;
        int second = 0;
        StringBuilder fibonachi = new StringBuilder();
        first = scanner.nextInt();
        scanner.nextLine();
        second = scanner.nextInt();
        scanner.nextLine();
        scanner.close();
        if (first == 0) {
            fibonachi.append("0 1 ");
        } else if (first == 1) {
            fibonachi.append("1 ");
        }
        for (int i = 2; i < second; i++) {
            currNum = currFirst + currSecond;
            currFirst = currSecond;
            currSecond = currNum;
            if (i >= first) {
                fibonachi.append(currSecond).append(" ");
            }
        }
        System.out.println(fibonachi.toString());
    }
}
