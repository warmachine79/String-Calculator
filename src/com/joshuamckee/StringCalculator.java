package com.joshuamckee;

import java.util.Arrays;

class StringCalculator
{
    StringBuilder delimiters = new StringBuilder(",\n");
    final static String DELIMITER_NOTATION = "//";
    final static int DELIMITER_NOTATION_LENGTH = 2;
    final static int NEW_LINE_CHAR_LENGTH = 1;
    int Add(String numbers)
    {
        if (numbers.startsWith(DELIMITER_NOTATION))
        {
            // skip delimiter signifying new delimiter
            delimiters.append(numbers, DELIMITER_NOTATION_LENGTH, numbers.indexOf("\n"));
            // skip newline token
            numbers = numbers.substring(numbers.indexOf("\n")
                + NEW_LINE_CHAR_LENGTH);
        }
        delimiters.insert(0, "[").append("]");
        return Arrays.stream(numbers.split(delimiters.toString()))
            .filter((s) -> s.matches("\\d+"))
            .mapToInt(Integer::valueOf)
            .sum();
    }
}
