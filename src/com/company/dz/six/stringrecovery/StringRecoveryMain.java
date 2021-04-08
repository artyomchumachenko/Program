package com.company.dz.six.stringrecovery;

import java.util.HashMap;
import java.util.Scanner;

public class StringRecoveryMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        final int numberOfStrings = 2;
        HashMap<Integer, String> retrievalStringBuffer = new HashMap<>();
        String[] string = new String[numberOfStrings];

        for (int currString = 0; currString < numberOfStrings; currString++) {
            System.out.println("Введите " + (currString + 1) + " строку");
            string[currString] = scanner.nextLine();
        }
        scanner.close();
        String[] firstSplitString = string[0].split(";");
        for (String set : firstSplitString) {
            String[] partOfTheSet = set.split(":");
            retrievalStringBuffer.put(Integer.valueOf(partOfTheSet[1]), partOfTheSet[0]);
        }
        StringBuilder retrievalString = new StringBuilder();
        String[] secondSplitString = string[1].split("-");
        for (String set : secondSplitString) {
            retrievalString.append(retrievalStringBuffer.getOrDefault
                    (Integer.valueOf(set), "_"));
        }
        System.out.println(retrievalString.toString());
    }
}
