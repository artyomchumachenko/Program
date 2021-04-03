package com.company.lesson;

public class Stats<T extends Number> {
    private final T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    double average() {
        double sum = 0;
        for (T num : nums) {
            sum += num.doubleValue();
        }
        return sum / nums.length;
    }
}
