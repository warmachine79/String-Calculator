package com.joshuamckee;

import java.util.Random;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest
{

    @Rule
    public ExpectedException ex = ExpectedException.none();

    StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void testEmptyStringAdd()
    {
        Assert.assertEquals(0, stringCalculator.Add(""));
    }

    @Test
    public void testAddOneNumber()
    {
        Assert.assertEquals(6, stringCalculator.Add("6"));
    }

    @Test
    public void testAddTwoNumbers()
    {
        Assert.assertEquals(15, stringCalculator.Add("9,6"));
    }

    @Test
    public void testRandomNumbers()
    {
        Random random = new Random();
        int numbers = random.nextInt(1000);
        StringBuilder numberList = new StringBuilder();
        int total = 0;
        for (int i = 0; i <= numbers; i++)
        {
            int number = random.nextInt(1000);
            numberList.append(number);
            numberList.append(",");
            total+=number;
        }
        Assert.assertEquals(total, stringCalculator.Add(numberList.toString()));
    }

    @Test
    public void testNewlines()
    {
        Assert.assertEquals(25, stringCalculator.Add("9,6\n5\n2,3"));
    }

    @Test
    public void testRandomNumbersNewlines()
    {
        Random random = new Random();
        int numbers = random.nextInt(10);
        StringBuilder numberList = new StringBuilder();
        int total = 0;
        for (int i = 0; i <= numbers; i++)
        {
            int number = random.nextInt(1000);
            numberList.append(number);
            if (0 == i % 2)
            {
                numberList.append(",");
            }
            else
            {
                numberList.append("\n");
            }
            total+=number;
        }
        Assert.assertEquals(total, stringCalculator.Add(numberList.toString()));
    }

    @Test
    public void testNewDelimiter()
    {
        Assert.assertEquals(3, stringCalculator.Add("//;\n1;2"));
        Assert.assertEquals(28, stringCalculator.Add("//;\n1;2,3\n4,5;6;7"));
    }

    @Test
    public void testNegativeNumbers()
    {
        ex.expect(Exception.class);
        ex.expectMessage("Negatives not allowed -2");
        stringCalculator.Add("//;\n1;-2,3,5;17");
    }

    @Test
    public void testMultipleNegativeNumbers()
    {
        ex.expect(Exception.class);
        ex.expectMessage("Negatives not allowed -6, -4, -2");
        stringCalculator.Add("//;\n1;-2,3\n-4,5;-6;17");
    }

    @Test
    public void testGetCalledCount()
    {
        testEmptyStringAdd();
        testAddOneNumber();
        testNewDelimiter();
        Assert.assertEquals(4, stringCalculator.GetCalledCount());
    }

    @Test
    public void testExcludeNumbersAboveOneThousand()
    {
        Assert.assertEquals(28, stringCalculator.Add("//;\n1;2,1002,3\n4,5;6,1005;7"));
    }
}