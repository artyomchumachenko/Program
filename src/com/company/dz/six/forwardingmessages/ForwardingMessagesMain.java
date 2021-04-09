package com.company.dz.six.forwardingmessages;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ForwardingMessagesMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> pairOfMailboxes = new HashMap<>();
        boolean flag = true;

        do {
            System.out.println("Введите пару почт");
            String inputPairOfMailboxes = scanner.nextLine();
            if (!inputPairOfMailboxes.matches("END")) {
                String[] splitPairMails = inputPairOfMailboxes.split(":");
                pairOfMailboxes.put(splitPairMails[0], splitPairMails[1]);
                flag = true;
            } else {
                flag = false;
            }
        } while (flag);
        ArrayList<String> firstSender = new ArrayList<>();
        for (String currMail : pairOfMailboxes.keySet()) {
            if (!pairOfMailboxes.containsValue(currMail)) {
                firstSender.add(currMail);
            }
        }
        for (String currMail : firstSender) {
            StringBuilder currResult = new StringBuilder();
            currResult.append(currMail);
            do {
                currMail = pairOfMailboxes.get(currMail);
                currResult.append(" > ").append(currMail);
            } while (pairOfMailboxes.containsKey(currMail));
            System.out.println(currResult.toString());
        }
    }
}
