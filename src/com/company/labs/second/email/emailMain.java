package com.company.labs.second.email;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class emailMain {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String emailString = new String();
        String regexEmail = new String("^[a-zA-Z0-9]+[\\_\\-\\+\\.]*[a-zA-Z0-9]+\\@((([a-zA-Z]+\\.)+[a-zA-Z]+)"
                + "|(((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]|[0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]"
                + "|[0-9]?)))$");
        System.out.println("Введите email: ");
        emailString = scanner.nextLine();
        scanner.close();
        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(emailString);
        if (matcherEmail.find()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}