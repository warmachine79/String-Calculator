
package com.joshuamckee;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class StringCalculator
{
    StringBuilder delimiters = new StringBuilder(",\n");
    final static String DELIMITER_NOTATION = "//";
    final static int DELIMITER_NOTATION_LENGTH = 2;
    final static int NEW_LINE_CHAR_LENGTH = 1;
    final static String NEGATIVE_MESSAGE = "Negatives not allowed ";
    final static String REGEX_EXPRESSION_INTEGERS = "-?\\d+";
    private int addCalledCount = 0;

    int Add(String numbers)
    {
        addCalledCount++;
        if (numbers.startsWith(DELIMITER_NOTATION))
        {
            // skip delimiter signifying new delimiter
            delimiters.append(numbers, DELIMITER_NOTATION_LENGTH, numbers.indexOf("\n"));
            // skip newline token
            numbers = numbers.substring(numbers.indexOf("\n")
                + NEW_LINE_CHAR_LENGTH);
        }
        delimiters.insert(0, "[").append("]");
        List<Integer> numbersList = Arrays.stream(numbers.split(delimiters.toString()))
            .filter((s) -> s.matches(REGEX_EXPRESSION_INTEGERS))
            .map(Integer::valueOf)
            .sorted()
            .collect(Collectors.toList());
        if (!numbersList.isEmpty() && numbersList.get(0) < 0)
        {
            StringBuilder exceptionMessage =
                new StringBuilder(NEGATIVE_MESSAGE);
            for (Integer i : numbersList)
            {
                if (i < 0)
                {
                    exceptionMessage.append(i).append(", ");
                }
                else
                {
                    exceptionMessage.setLength(exceptionMessage.length()-2);
                    break;
                }
            }
            throw new RuntimeException(exceptionMessage.toString());
        }
        return numbersList.stream().mapToInt(Integer::valueOf).sum();
    }

    int GetCalledCount()
    {
        return this.addCalledCount;
    }
}
