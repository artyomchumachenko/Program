package com.company.dz.nine.pah;

public class TakeSize {
    public static int takeSize(Integer[][] array, int i, int j, int currJ) {
        int size = 0;
        if (i < array.length && j < array[i].length) {
            if (currJ > Math.max(array.length * array.length, array[i].length * array[i].length)) {
                return 0;
            }
            if (array[i][j] == 0) {
                size++;
                array[i][j] = 2;
                size += takeSize(array, i + 1, j, currJ + 1);
                if (j < array[i].length) {
                    size += takeSize(array, i, j + 1, currJ + 1);
                }
                if (i > 0) {
                    size += takeSize(array, i - 1, j, currJ + 1);
                }
                if (j > 0) {
                    size += takeSize(array, i, j - 1, currJ + 1);
                }
            }
        }
        return size;
    }
}
