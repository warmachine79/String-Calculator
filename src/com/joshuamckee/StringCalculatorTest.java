package com.joshuamckee;

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
}