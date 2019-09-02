package com.mory.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class DigitComposeTest {

    @Test
    public void convertDigitList() {
        List<Integer> digitList = new LinkedList<>();
        digitList.add(1);
        digitList.add(2);
        DigitCompose digitCompose = new DigitCompose(digitList);
        Assert.assertTrue("1,2".equals(digitCompose.getDigits()));
    }

    @Test
    public void equals() {
        List<Integer> digitList = new LinkedList<>();
        digitList.add(1);
        digitList.add(2);

        DigitCompose digitCompose0 = new DigitCompose(digitList);
        DigitCompose digitCompose1 = new DigitCompose(digitList);
        Assert.assertTrue(digitCompose0.equals(digitCompose1));
    }

    @Test
    public void isContainByondLetter(){
        List<Integer> digitList = new LinkedList<>();
        digitList.add(1);
        digitList.add(2);

        DigitCompose digitCompose0 = new DigitCompose(digitList);
        Assert.assertTrue(digitCompose0.isContainByondLetter() == true);
    }

}