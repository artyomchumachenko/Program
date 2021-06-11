package com.company.coursework.pizza;

import java.util.ArrayList;
import java.util.LinkedList;

public class City {
    private static final int ONE = 1;
    private static int size = 8;
    static int[][] connectionMatrix = {
            {0, 20, 0, 10, 30, 40, 50, 0},
            {20, 0, 30, 0, 0, 0, 0, 10},
            {0, 30, 0, 70, 0, 0, 0, 0},
            {10, 0, 70, 0, 40, 0, 0, 0},
            {30, 0, 0, 40, 0, 50, 0, 0},
            {40, 0, 0, 0, 50, 0, 20, 0},
            {50, 0, 0, 0, 0, 20, 0, 0},
            {0, 10, 0, 0, 0, 0, 0, 0}
    };

    public static final ArrayList<String> nameDistricts = new ArrayList<>();
    static int courierDistrict = 0;

    public City() {
        nameDistricts.add("Пиццерия");
        nameDistricts.add("Полянка");
        nameDistricts.add("Сокол");
        nameDistricts.add("Театральная");
        nameDistricts.add("ВДНХ");
        nameDistricts.add("Китай-город");
        nameDistricts.add("Динамо");
        nameDistricts.add("Аэропорт");
    }

    public void addVertex(String nameVertex, LinkedList<Integer> connections, LinkedList<Integer> connectValues) {
        City.nameDistricts.add(nameVertex);
        size++;
        int[][] buffer = connectionMatrix;
        connectionMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != size - ONE && j != size - ONE) {
                    connectionMatrix[i][j] = buffer[i][j];
                } else {
                    if (j == size - ONE) {
                        for (Integer vertex : connections) {
                            int numberIndex = connections.indexOf(vertex);
                            if (i != vertex) {
                                connectionMatrix[i][j] = 0;
                            } else {
                                connectionMatrix[i][j] = connectValues.get(numberIndex);
                            }
                        }
                    } else {
                        for (Integer vertex : connections) {
                            int numberIndex = connections.indexOf(vertex);
                            if (j != vertex) {
                                connectionMatrix[i][j] = 0;
                            } else {
                                connectionMatrix[i][j] = connectValues.get(numberIndex);
                                connections.remove(numberIndex);
                                connectValues.remove(numberIndex);
                            }
                        }
                    }
                }
            }
        }
        // Проверка расширения матрицы смежности
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                System.out.print(connectionMatrix[i][j] + "\t");
//            }
//            System.out.println();
//        }
    }

    public void makeRoad(int dest) {
        int[] minDistance = new int[size];       // минимальное расстояние
        int[] visitVertexs = new int[size];       // посещенные вершины
        int temp;
        int minIndex;
        int min;
        int begin_index = courierDistrict;
        //Инициализация вершин и расстояний
        int maxValue = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minDistance[i] = maxValue;
            visitVertexs[i] = 1;
        }
        minDistance[begin_index] = 0;
        // Шаг алгоритма
        do {
            minIndex = maxValue;
            min = maxValue;
            for (int i = 0; i < size; i++) { // Если вершину ещё не обошли и вес меньше min
                if ((visitVertexs[i] == 1) && (minDistance[i] < min)) { // Переприсваиваем значения
                    min = minDistance[i];
                    minIndex = i;
                }
            }
            // Добавляем найденный минимальный вес
            // к текущему весу вершины
            // и сравниваем с текущим минимальным весом вершины
            if (minIndex != maxValue) {
                for (int i = 0; i < size; i++) {
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
        // Восстановление пути
        int[] vertexIsVisited = new int[size];       // массив посещенных вершин
        int end = dest;         // индекс конечной вершины = 5 - 1
        vertexIsVisited[0] = end;    // начальный элемент - конечная вершина
        int k = 1;           // индекс предыдущей вершины
        int weight = minDistance[end]; // вес конечной вершины

        while (end != begin_index) // пока не дошли до начальной вершины
        {
            for (int i = 0; i < size; i++) // просматриваем все вершины
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
        StringBuilder result = new StringBuilder();
        result.append(minDistance[dest]).append(" минут").append(" | ");
        // Вывод пути (начальная вершина оказалась в конце массива из k элементов)
        System.out.println("\nВывод кратчайшего пути");
        result.append(nameDistricts.get(vertexIsVisited[k - 1]));
        for (int i = k - 2; i >= 0; --i) {
            result.append(" -> ").append(nameDistricts.get(vertexIsVisited[i]));
        }
        System.out.println(result.toString());
        courierDistrict = dest;
    }
}