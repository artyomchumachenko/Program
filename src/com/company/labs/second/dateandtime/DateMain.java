package com.company.labs.second.dateandtime;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DateMain {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String dateCode = new String();
        String regexForDateCode = new String("^([0-9]{4})-((02-(2[0-8]|[01][0-9]))|"
                + "((01|03|05|07|08|10|12)-"
                + "(3[0-1]|[0-2][0-9]))|((04|06|09|11)-(30|[0-2][0-9])))"
                + "[ T](2[0-3]|[0-1][0-9](:[0-5][0-9]){2})Z{0,1}"
                + "(\\+(1[0-4]|0[0-9])([0-5][0-9]){0,1}(:[0-5][0-9]){0,1}){0,1}"
                + "(\\-(1[0-2]|0[0-9])([0-5][0-9]){0,1}(:[0-5][0-9]){0,1}){0,1}$");
        System.out.println("Введите дату и время в формате ISO 8601: ");
        dateCode = scanner.nextLine();
        scanner.close();
        Pattern patternDateCode = Pattern.compile(regexForDateCode);
        Matcher matcherDateCode = patternDateCode.matcher(dateCode);
        System.out.println(matcherDateCode.find());
    }
}
