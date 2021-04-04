package com.company.games.sea.battle;

public class Battlefield {

    private final int size = 10;
    private final int field[][] = new int[size][size];
    private final String[]

    public Battlefield() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = State.EMPTY;
            }
        }
    }

    public void show() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("  1  2  3  4  5  6  7  8  9  10");

    }
}
