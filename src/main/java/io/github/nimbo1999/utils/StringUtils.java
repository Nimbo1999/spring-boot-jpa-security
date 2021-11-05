package io.github.nimbo1999.utils;

public class StringUtils {
    public static String removeNonDigits(String str) {
        return str.replaceAll("[^0-9]", "");
    }
}
