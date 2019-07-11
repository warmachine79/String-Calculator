
package com.joshuamckee;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class StringCalculator
{
    private static final String MULTI_CHAR_DELIMITER_NOTATION = "//[";
    private static final String DELIMITER_NOTATION = "//";
    private static final int DELIMITER_NOTATION_LENGTH = 2;
    private static final int NEW_LINE_CHAR_LENGTH = 1;
    private static final String NEGATIVE_MESSAGE = "Negatives not allowed ";
    private static final String REGEX_EXPRESSION_INTEGERS = "-?\\d+";
    private static final int INTEGER_THRESHOLD = 1000;
    private int addCalledCount = 0;

    int Add(String numbers)
    {
        StringBuilder delimiters = new StringBuilder(",\n");
        StringBuilder multiCharDelimiters = new StringBuilder();
        addCalledCount++;
        if (numbers.startsWith(DELIMITER_NOTATION))
        {
            if (numbers.startsWith(MULTI_CHAR_DELIMITER_NOTATION))
            {
                // skip delimiter signifying new delimiter
                multiCharDelimiters.append(numbers, DELIMITER_NOTATION_LENGTH,
                    numbers.indexOf("\n"));
                int index = multiCharDelimiters.indexOf("[");
                while (index >= 0)
                {
                    multiCharDelimiters.insert(index, "|");
                    index = multiCharDelimiters.indexOf("[", index+2);
                }
            }
            else
            {
                // skip delimiter signifying new delimiter
                delimiters.append(numbers, DELIMITER_NOTATION_LENGTH, numbers.indexOf("\n"));
            }
            // skip newline token
            numbers = numbers.substring(numbers.indexOf("\n")
                + NEW_LINE_CHAR_LENGTH);
        }
        delimiters.insert(0, "[").append("]");
        delimiters.append(multiCharDelimiters);
        List<Integer> numbersList = Arrays.stream(numbers.split(delimiters.toString()))
            .filter((s) -> s.matches(REGEX_EXPRESSION_INTEGERS))
            .map(Integer::valueOf)
            .sorted()
            .filter(i -> i<INTEGER_THRESHOLD)
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
