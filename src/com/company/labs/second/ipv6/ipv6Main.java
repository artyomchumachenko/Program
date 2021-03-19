package com.company.labs.second.ipv6;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ipv6Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String addressString = new String();
        String regexAddressString = new String("^([0-9a-fA-F]{0,4}:){2,7}([0-9a-fA-F]{0,4})$");
        System.out.println("Введите IPv6 адрес: ");
        addressString = scanner.nextLine();
        scanner.close();
        Pattern patternAddress = Pattern.compile(regexAddressString);
        Matcher matcherAddress = patternAddress.matcher(addressString);
        System.out.println(matcherAddress.find());
    }
}
