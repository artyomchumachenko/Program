package com.company.labs.second.mac;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MacMain {
    public static void main(String[] args) {
        String regexForMac = new String("^([0-9A-Fa-f]{2}:){5}([0-9A-Fa-f]{2})$");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        scanner.close();
        Pattern patternMac = Pattern.compile(regexForMac);
        Matcher matcherMac = patternMac.matcher(inputString);
        System.out.println(matcherMac.find());
    }
}
