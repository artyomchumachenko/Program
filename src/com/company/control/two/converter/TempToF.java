package com.company.control.two.converter;

import com.company.control.two.converter.Converter;

public class TempToF implements Converter {
    public static double converterTemp(double degree) {
        return degree * 1.8 + 32;
    }
}
