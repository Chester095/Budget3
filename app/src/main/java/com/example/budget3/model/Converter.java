package com.example.budget3.model;

import androidx.databinding.InverseMethod;

public abstract class Converter {

    @InverseMethod("stringToInt")
    public static String intToString(int value) {
        System.out.println("SOUT -  Converter = " + value);
        return String.valueOf(value);
    }

    public static int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @InverseMethod("stringToDouble")
    public static String doubleToString(double value) {
        System.out.println("SOUT -  Converter = " + value);
        return String.valueOf(value);
    }

    public static double stringToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
