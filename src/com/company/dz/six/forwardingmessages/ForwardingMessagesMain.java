package com.company.dz.six.forwardingmessages;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ForwardingMessagesMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> mainChain = new ArrayList<>();
        final String end = new String("^END$");
        boolean flag = true;

        do {
            System.out.println("Введите строку");
            String inputString = scanner.nextLine();
            if (inputString.matches(end)) {
                flag = false;
            } else {
                emails.add(inputString.replace(":", " > "));
                flag = true;
            }
        } while (flag);
        for (String email : emails) {
            System.out.println(email);
            System.out.println("--------------");
        }
    }
}
