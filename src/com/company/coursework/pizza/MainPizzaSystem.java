package com.company.coursework.pizza;

import com.company.coursework.pizza.Users.Client;

import java.util.HashMap;
import java.util.Scanner;

public class MainPizzaSystem {
    static HashMap<String, Client> users = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static City city = new City();

    public static void main(String[] args) {
        System.out.println("Hello world!");
        mainMenu();
    }

    private static void mainMenu() {
        String choose = "";
        while (!choose.equals("end")) {
            System.out.println("[reg] - Регистрация");
            System.out.println("[ent] - Вход");
            System.out.println("[end] - Выйти");
            choose = scanner.nextLine().toLowerCase();
            switch (choose) {
                case "reg":
                    System.out.println("Введите имя: ");
                    String nickname = scanner.nextLine();
                    System.out.println("Введите логин: ");
                    String login = scanner.nextLine();
                    System.out.println("Введите пароль: ");
                    String password = scanner.nextLine();
                    System.out.println("Выберите номер вашего района: ");
                    StringBuilder bufferDistricts = new StringBuilder();
                    for (int i = 0; i < City.nameDistricts.size(); i++) {
                        bufferDistricts.append(i + 1).append(") ").append(City.nameDistricts.get(i)).append("\n");
                    }
                    System.out.print(bufferDistricts.toString());
                    int district = scanner.nextInt();
                    scanner.nextLine();
                    users.put(login, new Client(nickname, login, password, district - 1));
                    break;
                case "ent":
                    System.out.println("Введите логин: ");
                    String takeLogin = scanner.nextLine();
                    if (users.containsKey(takeLogin)) {
                        boolean correctPassword = true;
                        do {
                            System.out.println("Введите пароль: ");
                            String takePassword = scanner.nextLine();
                            if (users.get(takeLogin).enter(takeLogin, takePassword)) {
                                correctPassword = false;
                                System.out.println("Вход выполнен!");
                                System.out.println("Хотите заказать пиццу? [yes] - Да / [no] - Нет");
                                boolean isYesOrNo = true;
                                String takeCommand;
                                do {
                                    takeCommand = scanner.nextLine().toLowerCase();
                                    if (takeCommand.equals("yes")) {
                                        city.makeRoad(users.get(takeLogin).getDist());
                                        isYesOrNo = false;
                                    } else if (takeCommand.equals("no")) {
                                        System.out.println("Заказ пиццы отменён");
                                        isYesOrNo = false;
                                    } else {
                                        System.out.println("Неизвестый выбор, повторите попытку!");
                                        isYesOrNo = true;
                                    }
                                } while (isYesOrNo);
                            } else {
                                System.out.println("Неверный пароль!");
                                System.out.println("[rep] - Повторить попытку / [end] - Выйти");
                                String commandPass = scanner.nextLine().toLowerCase();
                                if (commandPass.equals("rep")) {
                                    correctPassword = true;
                                } else if (commandPass.equals("end")) {
                                    correctPassword = false;
                                } else {
                                    System.out.println("Неизвестная команда, завершаем работу!");
                                    correctPassword = false;
                                }
                            }
                        } while (correctPassword);
                    } else {
                        System.out.println("Такого логина нет в базе");
                    }
                    break;
                default:
                    System.out.println("Такой команды нет");
                    break;
            }
        }
    }
}
