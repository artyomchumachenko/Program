package com.company.labs.second.url;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMain {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String addressString;
        String regexAddressString = new String("^((http:|https:)//)?[A-Za-z0-9][-A-Za-z0-9]*"
                + "[A-Za-z0-9](\\\\.[A-Za-z0-9][-A-Za-z0-9]*[A-Za-z0-9])*(:[0-9]+)?"
                + "(/([A-Za-z0-9._]|%20)+)*(\\\\?([A-Za-z0-9]|%20)+=([A-Za-z0-9]|%20)+"
                + "(&([A-Za-z0-9]|%20)+=([A-Za-z0-9]|%20)+)*)?(#[A-Za-z0-9]+)?$");
        System.out.println("Введите URL: ");
        addressString = scanner.nextLine();
        scanner.close();
        Pattern patternAddress = Pattern.compile(regexAddressString);
        Matcher matcherAddress = patternAddress.matcher(addressString);
        System.out.println(matcherAddress.find());
    }
}
