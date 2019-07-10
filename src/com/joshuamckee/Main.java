package com.joshuamckee;

import com.joshuamckee.StringCalculator;

public class Main {

    public static void main(String[] args) {
        // write your code here
        StringCalculator stringCalculator = new StringCalculator();
        System.out.println("Answer: " + stringCalculator.Add(args[0]));
    }
}
