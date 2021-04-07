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
        String regexStream = new String("STREAM\\s[1-3]");
        String regexNumber = new String("^[+,-]?[0-9]+$");
        boolean flag = true;
        int currStream = 1;
        do {
            String inputLine = scanner.nextLine();
            if (inputLine.matches(regexStream) || inputLine.matches("END")) {
                switch (inputLine) {
                    case "STREAM 1": {
                        currStream = 1;
                        break;
                    }
                    case "STREAM 2": {
                        currStream = 2;
                        break;
                    }
                    case "STREAM 3": {
                        currStream = 3;
                        break;
                    }
                    case "END": {
                        currStream = 0;
                        flag = false;
                        break;
                    }
                    default: {
                        System.out.println("Поток не существует");
                        break;
                    }
                }
            } else if (inputLine.matches(regexNumber)) {
                switch (currStream) {
                    case 1: {
                        firstStream.add(Integer.valueOf(inputLine));
                        break;
                    }
                    case 2: {
                        secondStream.add(Integer.valueOf(inputLine));
                        break;
                    }
                    case 3: {
                        threeStream.add(Integer.valueOf(inputLine));
                        break;
                    }
                    case 0: {
                        flag = false;
                        break;
                    }
                    default: {
                        System.out.println("Неизвестный поток");
                        break;
                    }
                }
            } else {
                System.out.println("Неизвестный формат ввода");
            }
        } while (flag);
        scanner.close();
        int maxNumbersOfCollections =
                Math.max(firstStream.size(), Math.max(secondStream.size(), threeStream.size()));
        if (maxNumbersOfCollections != 0) {
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
