package com.example.budget3.model;

import androidx.databinding.InverseMethod;

public abstract class Converter {

    @InverseMethod("stringToInt")
    public static String intToString(int value) {
        return String.valueOf(value);
    }

    public static int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
