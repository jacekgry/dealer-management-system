package com.jacekgry.cardealership.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.stream.Stream;

@UtilityClass
public class Utils {

    public static BigDecimal parseBigDecimal(String inputString, String inputField) {
        return parseBigDecimal(inputString, inputField, false);
    }

    public static BigDecimal parseBigDecimal(String inputString, String inputField, boolean max) {
        BigDecimal parsed;
        if ("".equals(inputString)) {
            parsed = max ? BigDecimal.valueOf(Integer.MAX_VALUE) : BigDecimal.ZERO;
        } else {
            try {
                parsed = new BigDecimal(inputString);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(inputField + " must be a number");
            }
        }
        return parsed;
    }

    public static String joinStringsWithDelimiter(Stream<String> stringsStream, String delimiter) {
        return stringsStream.reduce((s1, s2) -> String.join(delimiter, s1, s2)).orElse("");
    }
//
//    public static String joinStringsWithDelimiter(List<String> strings, String delimiter) {
//        return stringsStream.reduce((s1, s2) -> String.join(delimiter, s1, s2)).orElse("");
//    }
}
