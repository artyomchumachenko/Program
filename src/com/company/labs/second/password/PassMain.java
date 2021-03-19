package com.company.labs.second.password;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PassMain {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String passwordString = new String();
        String regexPassword = new String("^((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(\\_{0,}).{8,})$");
        System.out.println("Введите пароль: ");
        passwordString = scanner.nextLine();
        Pattern patternPassword = Pattern.compile(regexPassword);
        Matcher matcherPassword = patternPassword.matcher(passwordString);
        if (matcherPassword.find()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
