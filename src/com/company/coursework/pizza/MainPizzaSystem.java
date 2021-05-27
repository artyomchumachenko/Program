package com.company.coursework.pizza;

import com.company.coursework.pizza.Users.Client;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class MainPizzaSystem {
    static HashMap<String, Client> users = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static City city = new City();
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String FIRST_FLAG = "-f";
    private static final String SECOND_FLAG = "--file";
    private static final String END = "end";
    private static final String REGISTRATION = "reg";
    private static final String ENTER = "ent";
    private static final String REPEAT = "rep";
    private static final int NUMBER_OF_ARGS = 2;
    static LinkedList<String> commandsAndData = new LinkedList<>();
    static Deque<String> commandsFromFile = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        if (args.length == NUMBER_OF_ARGS
                && (args[0].equalsIgnoreCase(FIRST_FLAG) || args[0].equalsIgnoreCase(SECOND_FLAG))) {
            try (BufferedReader bufReader = new BufferedReader(new FileReader(args[1]))) {
                String s;
                while ((s = bufReader.readLine()) != null) {
                    commandsFromFile.add(s);
                }
                mainRunFile();
            } catch (IOException e) {
                System.out.println("Файл не найден. Проверьте правильность имени и повторите попытку.");
                mainMenu();
            }
        }
    }

    private static void mainMenu() throws IOException {
        String choose = "";
        while (!choose.equals(END)) {
            System.out.println("[reg] - Регистрация");
            System.out.println("[ent] - Вход");
            System.out.println("[end] - Выйти из программы");
            choose = scanner.nextLine().toLowerCase();
            if (!choose.equals(END)) {
                commandsAndData.add(choose);
            }
            switch (choose) {
                case REGISTRATION -> {
                    System.out.println("Введите имя: ");
                    String nickname = scanner.nextLine();
                    commandsAndData.add(nickname);
                    System.out.println("Введите логин: ");
                    String login = scanner.nextLine();
                    commandsAndData.add(login);
                    System.out.println("Введите пароль: ");
                    String password = scanner.nextLine();
                    commandsAndData.add(password);
                    System.out.println("Выберите номер вашего района: ");
                    StringBuilder bufferDistricts = new StringBuilder();
                    for (int i = 0; i < City.nameDistricts.size(); i++) {
                        bufferDistricts.append(i + 1).append(") ").append(City.nameDistricts.get(i)).append("\n");
                    }
                    System.out.print(bufferDistricts.toString());
                    int district = scanner.nextInt();
                    String districtString = Integer.toString(district);
                    commandsAndData.add(districtString);
                    scanner.nextLine();
                    users.put(login, new Client(nickname, login, password, district - 1));
                }
                case ENTER -> {
                    System.out.println("Введите логин: ");
                    String takeLogin = scanner.nextLine();
                    commandsAndData.add(takeLogin);
                    if (users.containsKey(takeLogin)) {
                        boolean correctPassword = true;
                        do {
                            System.out.println("Введите пароль: ");
                            String takePassword = scanner.nextLine();
                            commandsAndData.add(takePassword);
                            if (users.get(takeLogin).enter(takeLogin, takePassword)) {
                                correctPassword = false;
                                System.out.println("Вход выполнен!");
                                System.out.println("Хотите заказать пиццу? [yes] - Да / [no] - Нет");
                                boolean isYesOrNo = true;
                                do {
                                    String takeCommand = scanner.nextLine().toLowerCase();
                                    commandsAndData.add(takeCommand);
                                    if (takeCommand.equals(YES)) {
                                        city.makeRoad(users.get(takeLogin).getDist());
                                        isYesOrNo = false;
                                    } else if (takeCommand.equals(NO)) {
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
                                commandsAndData.add(commandPass);
                                if (commandPass.equals(REPEAT)) {
                                    correctPassword = true;
                                } else if (commandPass.equals(END)) {
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
                }
                case END -> {
                    System.out.println("[yes] - Сохранить данные в файл");
                    System.out.println("[no] - Завершить работу программы без сохранения");
                    String save = scanner.nextLine().toLowerCase();
                    if (save.equals(YES)) {
                        DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
                        File myFile = new File("result_" + timeStampPattern.format(java.time.LocalDateTime.now()) + ".txt");
                        FileWriter writer = null;
                        try {
                            writer = new FileWriter(myFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (String strAdd : commandsAndData) {
                            assert writer != null;
                            writer.write(strAdd);
                            writer.write(System.lineSeparator());
                        }
                        assert writer != null;
                        writer.flush();
                        writer.close();
                    }
                }
                default -> System.out.println("Такой команды нет");
            }
        }
    }

    private static String getStringValue(Scanner in) {
        boolean exceptionCaught = false;
        String inputString = null;

        do {
            exceptionCaught = false;
            try {
                inputString = in.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Вы не ввели ничего. Повторите попытку.");
                exceptionCaught = true;
                in.next();
            } catch (IllegalStateException e) {
                System.out.println("Система ввода оказалась в некорректном состоянии. Повторите попытку.");
                exceptionCaught = true;
                in = new Scanner(System.in);
                in.nextLine();
            }
        } while (exceptionCaught);
        return inputString;
    }

    private static void mainRunFile() throws IOException {
        String command = null;
        if (!commandsFromFile.isEmpty()) {
            command = commandsFromFile.pollFirst();
        } else {
            System.out.println("Файл пуст");
            mainMenu();
        }
        while (!commandsFromFile.isEmpty()) {
            System.out.println("[reg] - Регистрация");
            System.out.println("[ent] - Вход");
            System.out.println("[end] - Выйти из программы");
            System.out.println(command);
            switch (Objects.requireNonNull(command)) {
                case REGISTRATION -> {
                    System.out.println("Введите имя: ");
                    String nickname = commandsFromFile.pollFirst();
                    System.out.println(nickname);
                    commandsAndData.add(nickname);
                    System.out.println("Введите логин: ");
                    String login = commandsFromFile.pollFirst();
                    System.out.println(login);
                    commandsAndData.add(login);
                    System.out.println("Введите пароль: ");
                    String password = commandsFromFile.pollFirst();
                    System.out.println(password);
                    commandsAndData.add(password);
                    System.out.println("Выберите номер вашего района: ");
                    StringBuilder bufferDistricts = new StringBuilder();
                    for (int i = 0; i < City.nameDistricts.size(); i++) {
                        bufferDistricts.append(i + 1).append(") ").append(City.nameDistricts.get(i)).append("\n");
                    }
                    System.out.print(bufferDistricts.toString());
                    String districtString = commandsFromFile.pollFirst();
                    System.out.println(districtString);
                    commandsAndData.add(districtString);
                    assert districtString != null;
                    int district = Integer.parseInt(districtString);
                    users.put(login, new Client(nickname, login, password, district - 1));
                    command = commandsFromFile.pollFirst();
                }
                case ENTER -> {
                    System.out.println("Введите логин: ");
                    String takeLogin = commandsFromFile.pollFirst();
                    System.out.println(takeLogin);
                    commandsAndData.add(takeLogin);
                    if (users.containsKey(takeLogin)) {
                        boolean correctPassword = true;
                        do {
                            System.out.println("Введите пароль: ");
                            String takePassword = commandsFromFile.pollFirst();
                            System.out.println(takePassword);
                            commandsAndData.add(takePassword);
                            if (users.get(takeLogin).enter(takeLogin, takePassword)) {
                                correctPassword = false;
                                System.out.println("Вход выполнен!");
                                System.out.println("Хотите заказать пиццу? [yes] - Да / [no] - Нет");
                                boolean isYesOrNo = true;
                                do {
                                    String takeCommand = commandsFromFile.pollFirst();
                                    System.out.println(takeCommand);
                                    commandsAndData.add(takeCommand);
                                    if (Objects.equals(takeCommand, YES)) {
                                        city.makeRoad(users.get(takeLogin).getDist());
                                        isYesOrNo = false;
                                    } else if (Objects.equals(takeCommand, NO)) {
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
                                String commandPass = commandsFromFile.pollFirst();
                                System.out.println(commandPass);
                                commandsAndData.add(commandPass);
                                if (Objects.equals(commandPass, REPEAT)) {
                                    correctPassword = true;
                                } else if (Objects.equals(commandPass, END)) {
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
                    command = commandsFromFile.pollFirst();
                }
                default -> mainMenu();
            }
        }
        System.out.println();
        mainMenu();
    }
}
