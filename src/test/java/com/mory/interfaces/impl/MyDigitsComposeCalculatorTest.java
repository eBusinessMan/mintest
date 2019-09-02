package com.mory.interfaces.impl;

import com.mory.entity.DigitCompose;
import com.mory.utils.DigitComposeUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MyDigitsComposeCalculatorTest {

    @Test
    public void calculate() {
        List<DigitCompose> digitComposeList = new LinkedList<>();

        List<Integer> digitList = new LinkedList<>();
        digitList.add(1);
        DigitCompose digitCompose_1 = new DigitCompose(digitList);
        digitComposeList.add(digitCompose_1);

        digitList.add(2);
        DigitCompose digitCompose_12 = new DigitCompose(digitList);
        digitComposeList.add(digitCompose_12);

        digitList.add(3);
        DigitCompose digitCompose_123 = new DigitCompose(digitList);
        digitComposeList.add(digitCompose_123);

        DigitCompose digitCompose_13 = DigitComposeUtils.compose(digitCompose_1, 3);
        digitComposeList.add(digitCompose_13);
        DigitCompose digitCompose_2 = new DigitCompose(2);
        digitComposeList.add(digitCompose_2);
        DigitCompose digitCompose_23 = DigitComposeUtils.compose(digitCompose_2, 3);
        digitComposeList.add(digitCompose_23);
        DigitCompose digitCompose_3 = new DigitCompose(3);
        digitComposeList.add(digitCompose_3);

        int count = 7;

        MyDigitsComposeCalculator myDigitsComposeCalculator = new MyDigitsComposeCalculator();
        Set<DigitCompose> digitComposeSet = myDigitsComposeCalculator.calculate(digitList);

        Assert.assertTrue(7 == digitComposeSet.size());
        Assert.assertTrue(digitComposeSet.containsAll(digitComposeList));

        DigitCompose digitCompose_456 = new DigitCompose(456);
        Assert.assertFalse(digitComposeSet.contains(digitCompose_456));
    }
}