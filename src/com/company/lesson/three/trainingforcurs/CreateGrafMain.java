package com.company.lesson.three.trainingforcurs;

public class CreateGrafMain {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4
        graph.addVertex('F'); // 5
        graph.addEdge(0, 1, 1); // AB
        graph.addEdge(1, 2, 1); // BC
        graph.addEdge(2, 3, 1); // CD
        graph.addEdge(0, 4, 1); // AE
        graph.addEdge(4, 5, 1); // EF

        graph.passInWidth(2);
    }
}
