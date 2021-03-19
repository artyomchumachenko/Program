package com.company.labs.second.url;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMain {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String addressString;
        String regexAddressString = new String("^((http:\\/\\/|https:\\/\\/){0,1}(www\\.){0,1}"
                + "([a-zA-Z0-9]{1,}\\.){1,5}([a-zA-Z]{2,}){1}(:[1-9]{1}[0-9]{0,}){0,1}(\\/[a-zA-Z0-9]{1,}){0,}"
                + "(\\?(([a-zA-Z0-9]{1,}\\&{0,1}[a-zA-Z0-9]{1,})|([a-zA-Z0-9]{1}))((\\=[a-zA-Z0-9]{1,}\\&{0,1}"
                + "[a-zA-Z0-9]{1,})|(\\=[a-zA-Z0-9]{1})){0,}){0,})$");
        System.out.println("Введите URL: ");
        addressString = scanner.nextLine();
        scanner.close();
        Pattern patternAddress = Pattern.compile(regexAddressString);
        Matcher matcherAddress = patternAddress.matcher(addressString);
        System.out.println(matcherAddress.find());
    }
}
