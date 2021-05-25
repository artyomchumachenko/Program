package com.company.coursework.pizza;

import java.util.ArrayList;
import java.util.Scanner;

public class City {
    private static int maxValue = Integer.MAX_VALUE;
    private static final int SIZE = 4;
    static Scanner scanner = new Scanner(System.in);
    static int[][] connectionMatrix = {
            {0, 0, 10, 0},
            {0, 0, 5, 0},
            {10, 5, 0, 50},
            {0, 0, 50, 0},
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
        int[] minDistance = new int[SIZE];       // минимальное расстояние
        int[] visitVertexs = new int[SIZE];       // посещенные вершины
        int temp;
        int minIndex;
        int min;
        int begin_index = courierDistrict;
        //Инициализация вершин и расстояний
        for (int i = 0; i < SIZE; i++) {
            minDistance[i] = maxValue;
            visitVertexs[i] = 1;
        }
        minDistance[begin_index] = 0;
        // Шаг алгоритма
        do {
            minIndex = maxValue;
            min = maxValue;
            for (int i = 0; i < SIZE; i++) { // Если вершину ещё не обошли и вес меньше min
                if ((visitVertexs[i] == 1) && (minDistance[i] < min)) { // Переприсваиваем значения
                    min = minDistance[i];
                    minIndex = i;
                }
            }
            // Добавляем найденный минимальный вес
            // к текущему весу вершины
            // и сравниваем с текущим минимальным весом вершины
            if (minIndex != maxValue) {
                for (int i = 0; i < SIZE; i++) {
                    if (connectionMatrix[minIndex][i] > 0) {
                        temp = min + connectionMatrix[minIndex][i];
                        if (temp < minDistance[i]) {
                            minDistance[i] = temp;
                        }
                    }
                }
                visitVertexs[minIndex] = 0;
            }
        } while (minIndex < maxValue);
        // Вывод кратчайших расстояний до вершин
//        System.out.println("\nКратчайшие расстояния до вершин: ");
//        for (int i = 0; i < SIZE; i++) {
//            System.out.println(minDistance[i] + " ");
//        }

        // Восстановление пути
        int[] vertexIsVisited = new int[SIZE];       // массив посещенных вершин
        int end = dest;         // индекс конечной вершины = 5 - 1
        vertexIsVisited[0] = end;    // начальный элемент - конечная вершина
        int k = 1;           // индекс предыдущей вершины
        int weight = minDistance[end]; // вес конечной вершины

        while (end != begin_index) // пока не дошли до начальной вершины
        {
            for (int i = 0; i < SIZE; i++) // просматриваем все вершины
                if (connectionMatrix[i][end] != 0)        // если связь есть
                {
                    int tempSecond = weight - connectionMatrix[i][end]; // определяем вес пути из предыдущей вершины
                    if (tempSecond == minDistance[i])              // если вес совпал с рассчитанным
                    {                              // значит из этой вершины и был переход
                        weight = tempSecond;             // сохраняем новый вес
                        end = i;                   // сохраняем предыдущую вершину
                        vertexIsVisited[k] = i;            // и записываем ее в массив
                        k++;
                        break;
                    }
                }
        }
        StringBuffer result = new StringBuffer();
        result.append(minDistance[dest]).append(" минут").append(" ");
        // Вывод пути (начальная вершина оказалась в конце массива из k элементов)
        System.out.println("\nВывод кратчайшего пути");
        result.append(nameDistricts.get(vertexIsVisited[k - 1]));
        for (int i = k - 2; i >= 0; --i) {
            result.append("->").append(nameDistricts.get(vertexIsVisited[i]));
        }
        System.out.println(result.toString());
        courierDistrict = dest;
        return null;
    }
}