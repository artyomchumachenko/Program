package com.company.dz.nine.pah;

import java.util.ArrayList;
import java.util.Arrays;

public class SizeSearch extends TakeSize {
    public static int[] sizeSearch(Integer[][] array) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    temp.add(takeSize(array, i, j, 0));
                }
            }
        }
        int[] result = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }
        Arrays.sort(result);
        return result;
    }
}
