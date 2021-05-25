package com.company.coursework.pizza;

import java.util.ArrayList;
import java.util.Scanner;

public class City {
    private static int max = Integer.MAX_VALUE;
    private static final int SIZE = 4;
    static Scanner scanner = new Scanner(System.in);
    static int[][] distance = {
            {0, max, 10, max},
            {max, 0, 5, max},
            {max, max, 0, 50},
            {max, max, max, 0},
    };
    public static final ArrayList<String> nameDistricts = new ArrayList<>();
    static int courierDistrict = 0;

    public City() {
        nameDistricts.add("Pizza House");
        nameDistricts.add("Polyanka");
        nameDistricts.add("Molodezh");
        nameDistricts.add("Teatr");
    }

    public String makeRoad(int dest) {
        int[][] a = new int[SIZE][SIZE]; // матрица связей
        int[] d = new int[SIZE];       // минимальное расстояние
        int[] v = new int[SIZE];       // посещенные вершины
        int temp;
        int minIndex;
        int min;
        int begin_index = 0;
        // Инициализация матрицы связей
        for (int i = 0; i < SIZE; i++) {
            a[i][i] = 0;
            for (int j = i + 1; j < SIZE; j++) {
                System.out.println("Введите расстояние " + (i + 1) + " - " + (j + 1) + ": ");
                temp = scanner.nextInt();
                scanner.nextLine();
                a[i][j] = temp;
                a[j][i] = temp;
            }
        }
        // Вывод матрицы связей
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.println(a[i][j] + " ");
            }
            System.out.println();
        }
        //Инициализация вершин и расстояний
        for (int i = 0; i < SIZE; i++) {
            d[i] = 10000;
            v[i] = 1;
        }
        d[begin_index] = 0;
        // Шаг алгоритма
        do {
            minIndex = 10000;
            min = 10000;
            for (int i = 0; i < SIZE; i++) { // Если вершину ещё не обошли и вес меньше min
                if ((v[i] == 1) && (d[i] < min)) { // Переприсваиваем значения
                    min = d[i];
                    minIndex = i;
                }
            }
            // Добавляем найденный минимальный вес
            // к текущему весу вершины
            // и сравниваем с текущим минимальным весом вершины
            if (minIndex != 10000) {
                for (int i = 0; i < SIZE; i++) {
                    if (a[minIndex][i] > 0) {
                        temp = min + a[minIndex][i];
                        if (temp < d[i]) {
                            d[i] = temp;
                        }
                    }
                }
                v[minIndex] = 0;
            }
        } while (minIndex < 10000);
        // Вывод кратчайших расстояний до вершин
        System.out.println("\nКратчайшие расстояния до вершин: ");
        for (int i = 0; i < SIZE; i++) {
            System.out.println(d[i] + " ");
        }

        // Восстановление пути
        int[] ver = new int[SIZE];       // массив посещенных вершин
        int end = 1;         // индекс конечной вершины = 5 - 1
        ver[0] = end + 1;    // начальный элемент - конечная вершина
        int k = 1;           // индекс предыдущей вершины
        int weight = d[end]; // вес конечной вершины

        while (end != begin_index) // пока не дошли до начальной вершины
        {
            for (int i = 0; i < SIZE; i++) // просматриваем все вершины
                if (a[i][end] != 0)        // если связь есть
                {
                    int tempSecond = weight - a[i][end]; // определяем вес пути из предыдущей вершины
                    if (tempSecond == d[i])              // если вес совпал с рассчитанным
                    {                              // значит из этой вершины и был переход
                        weight = tempSecond;             // сохраняем новый вес
                        end = i;                   // сохраняем предыдущую вершину
                        ver[k] = i + 1;            // и записываем ее в массив
                        k++;
                        break;
                    }
                }
        }
        // Вывод пути (начальная вершина оказалась в конце массива из k элементов)
        System.out.println("\nВывод кратчайшего пути");
        for (int i = k - 1; i >= 0; i--) {
            System.out.println(ver[i] + " ");
        }
        return null;
    }
}
