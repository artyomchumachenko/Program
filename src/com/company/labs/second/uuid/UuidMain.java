package com.company.labs.second.uuid;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UuidMain {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String idString;
        String regexIdString = new String("^[0-9a-f]{8}\\-([0-9a-f]{4}\\-){3}[0-9a-f]{12}$");
        System.out.println("Введите UUID: ");
        idString = scanner.nextLine();
        scanner.close();
        Pattern patternId = Pattern.compile(regexIdString);
        Matcher matcherId = patternId.matcher(idString);
        if (matcherId.find()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
