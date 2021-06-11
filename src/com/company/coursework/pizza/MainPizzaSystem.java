package com.company.coursework.pizza;

import com.company.coursework.pizza.users.Admin;
import com.company.coursework.pizza.users.Client;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class MainPizzaSystem {
    static HashMap<String, Client> users = new HashMap<>();
    static HashMap<String, Admin> admins = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static City city = new City();
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String FIRST_FLAG = "-f";
    private static final String SECOND_FLAG = "--file";
    private static final String END = "end";
    private static final String REGISTRATION = "reg";
    private static final String ENTER = "ent";
    private static final String ADMIN_MODE = "am";
    private static final String REPEAT = "rep";
    private static final int NUMBER_OF_ARGS = 2;
    private static final int ONE = 1;
    static LinkedList<String> commandsAndData = new LinkedList<>();
    static Deque<String> commandsFromFile = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        admins.put("admin", new Admin("Alex P", "admin", "admin"));
        admins.put("artyom", new Admin("Artyom", "artyom", "artyom"));
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
        } else {
            mainMenu();
        }
    }

    private static void mainMenu() throws IOException {
        String choose = "";
        while (!choose.equals(END)) {
            System.out.println("[reg] - Регистрация");
            System.out.println("[ent] - Вход");
            System.out.println("[am] - Administrator Mode");
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
                    StringBuilder bufferDistricts = new StringBuilder();
                    for (int i = 0; i < City.nameDistricts.size(); i++) {
                        bufferDistricts.append(i + 1).append(") ").append(City.nameDistricts.get(i)).append("\n");
                    }
                    System.out.print(bufferDistricts.toString());
                    String districtString;
                    int realDistrictNumber;
                    do {
                        System.out.println("Выберите номер вашего района: ");
                        int district = scanner.nextInt();
                        districtString = Integer.toString(district);
                        scanner.nextLine();
                        realDistrictNumber = district - ONE;
                    } while (realDistrictNumber < 0
                            || realDistrictNumber >= City.nameDistricts.size());
                    commandsAndData.add(districtString);
                    users.put(login, new Client(nickname, login, password, realDistrictNumber));
                }
                case ENTER -> {
                    System.out.println("Введите логин: ");
                    String takeLogin = scanner.nextLine();
                    commandsAndData.add(takeLogin);
                    if (users.containsKey(takeLogin)) {
                        boolean correctPassword;
                        do {
                            System.out.println("Введите пароль: ");
                            String takePassword = scanner.nextLine();
                            commandsAndData.add(takePassword);
                            if (users.get(takeLogin).enter(takeLogin, takePassword)) {
                                correctPassword = false;
                                System.out.println("Вход выполнен!");
                                System.out.println("Добрый день, " + users.get(takeLogin).getNickname() + "!");
                                System.out.println("Хотите заказать пиццу? [yes] - Да / [no] - Нет");
                                boolean isYesOrNo;
                                do {
                                    String takeCommand = scanner.nextLine().toLowerCase();
                                    commandsAndData.add(takeCommand);
                                    if (takeCommand.equals(YES)) {
                                        city.makeRoad(users.get(takeLogin).getDist());
                                        isYesOrNo = false;
                                    } else if (takeCommand.equals(NO)) {
                                        System.out.println("Заказ пиццы отменён.");
                                        isYesOrNo = false;
                                    } else {
                                        System.out.println("Неизвестый выбор, повторите попытку.");
                                        isYesOrNo = true;
                                    }
                                } while (isYesOrNo);
                            } else {
                                System.out.println("Неверный пароль.");
                                System.out.println("[rep] - Повторить попытку / [end] - Выйти");
                                String commandPass = scanner.nextLine().toLowerCase();
                                commandsAndData.add(commandPass);
                                if (commandPass.equals(REPEAT)) {
                                    correctPassword = true;
                                } else if (commandPass.equals(END)) {
                                    correctPassword = false;
                                } else {
                                    System.out.println("Неизвестная команда, завершаем работу.");
                                    correctPassword = false;
                                }
                            }
                        } while (correctPassword);
                    } else {
                        System.out.println("Такого логина нет в базе.");
                    }
                }
                case ADMIN_MODE -> {
                    System.out.println("Вы пытаетесь зайти в режим администратора!");
                    System.out.println("Введите логин: ");
                    String takeLogin = scanner.nextLine();
                    commandsAndData.add(takeLogin);
                    if (admins.containsKey(takeLogin)) {
                        boolean correctPassword;
                        do {
                            System.out.println("Введите пароль: ");
                            String takePassword = scanner.nextLine();
                            commandsAndData.add(takePassword);
                            if (admins.get(takeLogin).enter(takeLogin, takePassword)) {
                                correctPassword = false;
                                do {
                                    System.out.println("Хотите добавить новый район для доставки? [yes] - Да / [no] - Нет");
                                    String addVertex = scanner.nextLine().toLowerCase();
                                    commandsAndData.add(addVertex);
                                    if (addVertex.equals(NO)) {
                                        break;
                                    }
                                    if (addVertex.equals(YES)) {
                                        boolean regionIsExists = true;
                                        String nameVertex;
                                        do {
                                            System.out.println("Введите название района: ");
                                            nameVertex = scanner.nextLine();
                                            if (nameVertex.equalsIgnoreCase(END)) {
                                                System.out.println("Добавление нового района прервано!");
                                                break;
                                            }
                                            for (String obj : City.nameDistricts) {
                                                if (Objects.equals(obj.toLowerCase(), nameVertex.toLowerCase())) {
                                                    System.out.println("Район с таким названием уже существует, "
                                                            + "повторите попытку.");
                                                    regionIsExists = true;
                                                    break;
                                                } else {
                                                    regionIsExists = false;
                                                }
                                            }
                                        } while (regionIsExists);
                                        if (nameVertex.equalsIgnoreCase(END)) {
                                            break;
                                        } else {
                                            commandsAndData.add(nameVertex);
                                        }
                                        StringBuilder bufferRegions = new StringBuilder();
                                        for (int i = 0; i < City.nameDistricts.size(); i++) {
                                            bufferRegions.append(i + 1)
                                                         .append(") ")
                                                         .append(City.nameDistricts.get(i))
                                                         .append("\n");
                                        }
                                        System.out.print(bufferRegions.toString());
                                        boolean addVertexFlag;
                                        LinkedList<Integer> connectsVertex = new LinkedList<>();
                                        LinkedList<Integer> connectsValues = new LinkedList<>();
                                        do {
                                            int realDistrictNumber;
                                            String districtString;
                                            do {
                                                System.out.println("Выберите номер района к которому есть путь из нового: ");
                                                int district = scanner.nextInt();
                                                districtString = Integer.toString(district);
                                                scanner.nextLine();
                                                realDistrictNumber = district - ONE;
                                            } while (realDistrictNumber < 0
                                                    || realDistrictNumber >= City.nameDistricts.size());
                                            commandsAndData.add(districtString);
                                            int value;
                                            String districtValue;
                                            do {
                                                System.out.println("Введите время дороги к этому району: ");
                                                value = scanner.nextInt();
                                                districtValue = Integer.toString(value);
                                                scanner.nextLine();
                                            } while (value <= 0);
                                            commandsAndData.add(districtValue);
                                            connectsVertex.add(realDistrictNumber);
                                            connectsValues.add(value);
                                            System.out.println("Хотите добавить ещё один путь? [yes] - Да / [no] - Нет");
                                            String addNewRegion = scanner.nextLine().toLowerCase();
                                            addVertexFlag = addNewRegion.equals(YES);
                                        } while (addVertexFlag);
                                        city.addVertex(nameVertex, connectsVertex, connectsValues);
                                    } else {
                                        System.out.println("У администратора нет других возможностей :(");
                                    }
                                } while (true);
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
                                    System.out.println("Неизвестная команда, завершаем работу.");
                                    correctPassword = false;
                                }
                            }
                        } while (correctPassword);
                    } else {
                        System.out.println("Администратора с таким логином нет.");
                    }
                }
                case END -> {
                    System.out.println("[yes] - Сохранить данные в файл");
                    System.out.println("[no] - Завершить работу программы без сохранения");
                    String save = scanner.nextLine().toLowerCase();
                    if (save.equals(YES)) {
                        DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
                        File myFile = new File
                                ("result_" + timeStampPattern.format(java.time.LocalDateTime.now()) + ".txt");
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
                default -> System.out.println("Такой команды нет.");
            }
        }
        scanner.close();
    }

    private static void mainRunFile() throws IOException {
        String command = null;
        if (!commandsFromFile.isEmpty()) {
            command = commandsFromFile.pollFirst();
        } else {
            System.out.println("Файл пуст.");
            mainMenu();
        }
        while (!commandsFromFile.isEmpty()) {
            System.out.println("[reg] - Регистрация");
            System.out.println("[ent] - Вход");
            System.out.println("[am] - Administrator Mode");
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
                    StringBuilder bufferDistricts = new StringBuilder();
                    for (int i = 0; i < City.nameDistricts.size(); i++) {
                        bufferDistricts.append(i + 1).append(") ").append(City.nameDistricts.get(i)).append("\n");
                    }
                    System.out.print(bufferDistricts.toString());
                    System.out.println("Выберите номер вашего района: ");
                    String districtString = commandsFromFile.pollFirst();
                    System.out.println(districtString);
                    commandsAndData.add(districtString);
                    assert districtString != null;
                    int district = Integer.parseInt(districtString);
                    users.put(login, new Client(nickname, login, password, district - ONE));
                    command = commandsFromFile.pollFirst();
                }
                case ENTER -> {
                    System.out.println("Введите логин: ");
                    String takeLogin = commandsFromFile.pollFirst();
                    System.out.println(takeLogin);
                    commandsAndData.add(takeLogin);
                    if (users.containsKey(takeLogin)) {
                        boolean correctPassword;
                        do {
                            System.out.println("Введите пароль: ");
                            String takePassword = commandsFromFile.pollFirst();
                            System.out.println(takePassword);
                            commandsAndData.add(takePassword);
                            if (users.get(takeLogin).enter(takeLogin, takePassword)) {
                                correctPassword = false;
                                System.out.println("Вход выполнен!");
                                System.out.println("Добрый день, " + users.get(takeLogin).getNickname() + "!");
                                System.out.println("Хотите заказать пиццу? [yes] - Да / [no] - Нет");
                                boolean isYesOrNo;
                                do {
                                    String takeCommand = commandsFromFile.pollFirst();
                                    System.out.println(takeCommand);
                                    commandsAndData.add(takeCommand);
                                    if (Objects.equals(takeCommand, YES)) {
                                        city.makeRoad(users.get(takeLogin).getDist());
                                        isYesOrNo = false;
                                    } else if (Objects.equals(takeCommand, NO)) {
                                        System.out.println("Заказ пиццы отменён.");
                                        isYesOrNo = false;
                                    } else {
                                        System.out.println("Неизвестый выбор, повторите попытку.");
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
                                    System.out.println("Неизвестная команда, завершаем работу.");
                                    correctPassword = false;
                                }
                            }
                        } while (correctPassword);
                    } else {
                        System.out.println("Такого логина нет в базе.");
                    }
                    command = commandsFromFile.pollFirst();
                }
                case ADMIN_MODE -> {
                    System.out.println("Вы пытаетесь зайти в режим администратора!");
                    System.out.println("Введите логин: ");
                    String takeLogin = commandsFromFile.pollFirst();
                    commandsAndData.add(takeLogin);
                    if (admins.containsKey(takeLogin)) {
                        boolean correctPassword;
                        do {
                            System.out.println("Введите пароль: ");
                            String takePassword = commandsFromFile.pollFirst();
                            commandsAndData.add(takePassword);
                            if (admins.get(takeLogin).enter(takeLogin, takePassword)) {
                                correctPassword = false;
                                do {
                                    System.out.println("Хотите добавить новый район для доставки? [yes] - Да / [no] - Нет");
                                    String addVertex = commandsFromFile.pollFirst();
                                    commandsAndData.add(addVertex);
                                    assert addVertex != null;
                                    if (addVertex.equals(NO)) {
                                        break;
                                    }
                                    if (addVertex.equals(YES)) {
                                        boolean regionIsExists = true;
                                        String nameVertex;
                                        do {
                                            System.out.println("Введите название района: ");
                                            nameVertex = commandsFromFile.pollFirst();
                                            assert nameVertex != null;
                                            if (nameVertex.equalsIgnoreCase(END)) {
                                                System.out.println("Добавление нового района прервано!");
                                                break;
                                            }
                                            for (String obj : City.nameDistricts) {
                                                if (Objects.equals(obj.toLowerCase(), nameVertex.toLowerCase())) {
                                                    System.out.println("Район с таким названием уже существует, "
                                                            + "повторите попытку.");
                                                    regionIsExists = true;
                                                    break;
                                                } else {
                                                    regionIsExists = false;
                                                }
                                            }
                                        } while (regionIsExists);
                                        if (nameVertex.equalsIgnoreCase(END)) {
                                            break;
                                        } else {
                                            commandsAndData.add(nameVertex);
                                        }
                                        StringBuilder bufferRegions = new StringBuilder();
                                        for (int i = 0; i < City.nameDistricts.size(); i++) {
                                            bufferRegions.append(i + 1)
                                                         .append(") ")
                                                         .append(City.nameDistricts.get(i))
                                                         .append("\n");
                                        }
                                        System.out.print(bufferRegions.toString());
                                        boolean addVertexFlag;
                                        LinkedList<Integer> connectsVertex = new LinkedList<>();
                                        LinkedList<Integer> connectsValues = new LinkedList<>();
                                        do {
                                            int realDistrictNumber;
                                            String districtString;
                                            do {
                                                System.out.println("Выберите номер района к которому есть путь из нового: ");
                                                districtString = commandsFromFile.pollFirst();
                                                assert districtString != null;
                                                int district = Integer.parseInt(districtString);
                                                realDistrictNumber = district - ONE;
                                            } while (realDistrictNumber < 0
                                                    || realDistrictNumber >= City.nameDistricts.size());
                                            commandsAndData.add(districtString);
                                            int value;
                                            String districtValue;
                                            do {
                                                System.out.println("Введите время дороги к этому району: ");
                                                districtValue = commandsFromFile.pollFirst();
                                                assert districtValue != null;
                                                value = Integer.parseInt(districtValue);
                                            } while (value <= 0);
                                            commandsAndData.add(districtValue);
                                            connectsVertex.add(realDistrictNumber);
                                            connectsValues.add(value);
                                            city.addVertex(nameVertex, connectsVertex, connectsValues);
                                            System.out.println("Хотите добавить ещё один путь? [yes] - Да / [no] - Нет");
                                            String addNewRegion = commandsFromFile.pollFirst();
                                            assert addNewRegion != null;
                                            addVertexFlag = addNewRegion.equals(YES);
                                        } while (addVertexFlag);
                                    } else {
                                        System.out.println("У администратора нет других возможностей :(");
                                    }
                                } while (true);
                            } else {
                                System.out.println("Неверный пароль!");
                                System.out.println("[rep] - Повторить попытку / [end] - Выйти");
                                String commandPass = commandsFromFile.pollFirst();
                                commandsAndData.add(commandPass);
                                assert commandPass != null;
                                if (commandPass.equals(REPEAT)) {
                                    correctPassword = true;
                                } else if (commandPass.equals(END)) {
                                    correctPassword = false;
                                } else {
                                    System.out.println("Неизвестная команда, завершаем работу.");
                                    correctPassword = false;
                                }
                            }
                        } while (correctPassword);
                    } else {
                        System.out.println("Администратора с таким логином нет.");
                    }
                    command = commandsFromFile.pollFirst();
                }
                default -> {
                    System.out.println("Такой команды нет.");
                    command = commandsFromFile.pollFirst();
                }
            }
        }
        System.out.println();
        mainMenu();
    }
}
