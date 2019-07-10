package com.joshuamckee;

import java.util.Arrays;

class StringCalculator
{
    int Add(String numbers)
    {
        return Arrays.stream(numbers.split(","))
            .filter((s) -> s.matches("\\d+"))
            .mapToInt(Integer::valueOf)
            .sum();
    }
}
