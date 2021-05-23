package com.company.control.three.second;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        final int firstCom = 0;
        final int secondCom = 1;
        final int thirdCom = 2;
        final int fourCom = 3;
        HashMap<String, Integer> list = new HashMap<>();
        do {
            command = scanner.nextLine();
            if (command.matches("")) {
                break;
            }
            String[] currCommand = command.split("\s");
            if (currCommand[firstCom].equals("DEPOSIT")) {
                if (list.containsKey(currCommand[secondCom])) {
                    int currSum = list.get(currCommand[secondCom]);
                    int sum = Integer.parseInt(currCommand[thirdCom]);
                    currSum += sum;
                    list.put(currCommand[secondCom], currSum);
                } else {
                    int sum = Integer.parseInt(currCommand[thirdCom]);
                    list.put(currCommand[secondCom], sum);
                }
            } else if (currCommand[firstCom].equals("WITHDRAW")) {
                if (list.containsKey(currCommand[secondCom])) {
                    int currSum = list.get(currCommand[secondCom]);
                    int sum = Integer.parseInt(currCommand[thirdCom]);
                    currSum = currSum - sum;
                    list.put(currCommand[secondCom], currSum);
                } else {
                    int sum = Integer.parseInt(currCommand[thirdCom]);
                    sum = -sum;
                    list.put(currCommand[secondCom], sum);
                }
            } else if (currCommand[firstCom].equals("BALANCE")) {
                if (list.containsKey(currCommand[secondCom])) {
                    System.out.println(list.get(currCommand[secondCom]));
                } else {
                    System.out.println("ERROR");
                }
            } else if (currCommand[firstCom].equals("TRANSFER")) {
                if (!list.containsKey(currCommand[secondCom])) {
                    list.put(currCommand[secondCom], 0);
                }
                if (!list.containsKey(currCommand[thirdCom])) {
                    list.put(currCommand[thirdCom], 0);
                }
                int currSum1 = list.get(currCommand[secondCom]);
                int currSum2 = list.get(currCommand[thirdCom]);
                int sum = Integer.parseInt(currCommand[fourCom]);
                currSum2 += sum;
                currSum1 -= sum;
                list.put(currCommand[secondCom], currSum1);
                list.put(currCommand[thirdCom], currSum2);
            } else if (currCommand[firstCom].equals("INCOME")) {
                for (String currUser : list.keySet()) {
                    int sum = list.get(currUser);
                    if (sum > 0) {
                        float plusSum = secondCom + (Float.parseFloat(currCommand[secondCom])) / 100;
                        sum = (int) (sum * plusSum);
                        list.put(currUser, sum);
                    }
                }
            }
        } while (true);
    }
}
