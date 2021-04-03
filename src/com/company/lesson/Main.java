package com.company.lesson;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Stats<Long> stats = new Stats<>(new Long[]{1L, 2L, 3L, 5L});
        System.out.println(stats.average());
    }
}
