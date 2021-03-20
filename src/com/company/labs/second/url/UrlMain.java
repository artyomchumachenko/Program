package com.company.labs.second.url;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMain {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String addressString;
        String regexAddressString = new String("^(https://|http://)?([a-zA-Z0-9а-яА-ЯёЁ][a-zA-Z0-9_а-яА-ЯёЁ\\-_]*"
                + "\\.)+([a-zA-Zа-яА-ЯёЁ0-9]{2,})(:(6553[0-5]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|"
                + "[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9]))?(/[a-zA-Z0-9а-яА-ЯёЁ][a-zA-Z0-9_\\-а-яА-ЯёЁ]*)*"
                + "(/?\\?[a-zA-Z0-9\\-а-яА-ЯёЁ]+=[a-zA-Z0-9\\-а-яА-ЯёЁ]+(&[a-zA-Z0-9\\-а-яА-ЯёЁ]+=[a-zA-Z0-9\\-а-яА-ЯёЁ]+)*)*"
                + "(/?#[a-zA-Zа-яА-ЯёЁ0-9_\\-]+)*/?$");
        System.out.println("Введите URL: ");
        addressString = scanner.nextLine();
        scanner.close();
        Pattern patternAddress = Pattern.compile(regexAddressString);
        Matcher matcherAddress = patternAddress.matcher(addressString);
        System.out.println(matcherAddress.find());
    }
}
