package com.joshuamckee;

import com.sun.org.apache.xpath.internal.operations.String;
import java.util.Random;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest
{

    StringCalculator stringCalculator = new StringCalculator();
    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

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
}