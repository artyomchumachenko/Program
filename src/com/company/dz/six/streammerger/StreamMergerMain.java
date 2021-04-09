package com.company.dz.six.streammerger;

import java.util.ArrayList;
import java.util.Scanner;

public class StreamMergerMain {

    public static void main(String[] args) {
        final int firstMode = 1;
        final int secondMode = 2;
        final int threeMode = 3;
        final int zeroMode = 0;
        ArrayList<Integer> firstStream = new ArrayList<>();
        ArrayList<Integer> secondStream = new ArrayList<>();
        ArrayList<Integer> threeStream = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String regexStream = new String("STREAM\\s[1-3]");
        String regexNumber = new String("^[+-]?[0-9]+$");
        boolean flag = true;
        int currStream = firstMode;
        do {
            String inputLine = scanner.nextLine();
            if (inputLine.matches(regexStream) || inputLine.matches("END")) {
                switch (inputLine) {
                    case "STREAM 1":
                        currStream = firstMode;
                        break;
                    case "STREAM 2":
                        currStream = secondMode;
                        break;
                    case "STREAM 3":
                        currStream = threeMode;
                        break;
                    case "END":
                        currStream = zeroMode;
                        flag = false;
                        break;
                    default:
                        System.out.println("Поток не существует");
                        break;
                }
            } else if (inputLine.matches(regexNumber)) {
                switch (currStream) {
                    case firstMode:
                        firstStream.add(Integer.valueOf(inputLine));
                        break;
                    case secondMode:
                        secondStream.add(Integer.valueOf(inputLine));
                        break;
                    case threeMode:
                        threeStream.add(Integer.valueOf(inputLine));
                        break;
                    case zeroMode:
                        flag = false;
                        break;
                    default:
                        System.out.println("Неизвестный поток");
                        break;
                }
            } else {
                System.out.println("Неизвестный формат ввода");
            }
        } while (flag);
        scanner.close();
        int maxNumbersOfCollections =
                Math.max(firstStream.size(), Math.max(secondStream.size(), threeStream.size()));
        if (maxNumbersOfCollections != 0) {
            ArrayList<Integer> resultStream = new ArrayList<>();
            for (int currStep = 0; currStep < maxNumbersOfCollections; currStep++) {
                int numIndex = 0;
                if (currStep < firstStream.size()) {
                    numIndex += firstStream.get(currStep);
                }
                if (currStep < secondStream.size()) {
                    numIndex += secondStream.get(currStep);
                }
                if (currStep < threeStream.size()) {
                    numIndex += threeStream.get(currStep);
                }
                resultStream.add(numIndex);
            }
            for (Integer number : resultStream) {
                System.out.print(number + " ");
            }
        } else {
            System.out.println("Коллекции не заполнены");
        }
    }
}
