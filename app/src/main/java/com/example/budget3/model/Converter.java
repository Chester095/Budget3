package com.example.budget3.model;

import androidx.databinding.InverseMethod;

public abstract class Converter {

    @InverseMethod("stringToInt")
    public static String intToString(int value) {
        System.out.println("SOUT -  Converter intToString = " + value);
        return String.valueOf(value);
    }

    public static int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //TODO 17.05.2021 Не работает. Данные из OperationDatabase выгружаются, но при корректировке обнуляются
    @InverseMethod("stringToDouble")
    public static String doubleToString(double value) {
        System.out.println("SOUT -  Converter doubleToString = " + value);
        return String.valueOf(value);

    }

    public static double stringToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("SOUT -  Converter stringToDouble NumberFormatException = " + value);
            return -1;
        }
    }
}
