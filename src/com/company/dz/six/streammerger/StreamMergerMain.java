package com.company.dz.six.streammerger;

import java.util.ArrayList;
import java.util.Scanner;

public class StreamMergerMain {

    public static void main(String[] args) {
        ArrayList<Integer> firstStream = new ArrayList<>();
        ArrayList<Integer> secondStream = new ArrayList<>();
        ArrayList<Integer> threeStream = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> resultStream = new ArrayList<>();
        String call = new String("STREAM 1");
        boolean flag = true;
        do {
            switch (call) {
                case "STREAM 1":
                    String inputNumber = scanner.nextLine();
                    if (inputNumber.matches("^STREAM 2$") || inputNumber.matches("^STREAM 3$")) {
                        call = inputNumber;
                    } else {
                        firstStream.add(Integer.parseInt(inputNumber));
                    }
                    break;
                case "STREAM 2":
                    inputNumber = scanner.nextLine();
                    if (inputNumber.matches("^STREAM 1$") || inputNumber.matches("^STREAM 3$")) {
                        call = inputNumber;
                    } else {
                        secondStream.add(Integer.parseInt(inputNumber));
                    }
                    break;
                case "STREAM 3":
                    inputNumber = scanner.nextLine();
                    if (inputNumber.matches("^STREAM 1$") || inputNumber.matches("^STREAM 2$")) {
                        call = inputNumber;
                    } else {
                        threeStream.add(Integer.parseInt(inputNumber));
                    }
                    break;
                case "END":
                    flag = false;
                    break;
            }
        } while (flag);
        for (Integer first : firstStream) {
            System.out.println(first);
        }
        for (Integer second : secondStream) {
            System.out.println(second);
        }
        for (Integer three : threeStream) {
            System.out.println(three);
        }
    }
}
