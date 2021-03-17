package com.company.labs.second.ipv6;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ipv6Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String addressString = new String();
        String regexAddressString = new String("^([0-9Ea-f]{1,4}\\:){7}([0-9a-f]{1,4}){1}$");
        System.out.println("Введите IPv6 адрес: ");
        addressString = scanner.nextLine();
        scanner.close();
        Pattern patternAddress = Pattern.compile(regexAddressString);
        Matcher matcherAddress = patternAddress.matcher(addressString);
        if (matcherAddress.find()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
