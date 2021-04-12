package com.company.control.two.converter;

import java.util.Scanner;

public class СonvertMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите единицу в которую перевести");
        String degreeToConvert = scanner.nextLine();
        System.out.println("Введите градусы по C");
        double degree = scanner.nextDouble();
        scanner.close();
        switch (degreeToConvert) {
            case "K":
                System.out.println(String.format("%.2f", TempToK.converterTemp(degree)));
                break;
            case "F":
                System.out.println(String.format("%.2f", TempToF.converterTemp(degree)));
                break;
            case "N":
                System.out.println(String.format("%.2f", TempToN.converterTemp(degree)));
                break;
        }
    }
}
