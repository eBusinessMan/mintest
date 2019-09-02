package com.mory.interfaces.impl;

import com.mory.entity.DigitCompose;
import com.mory.utils.DigitComposeUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MyParallelLettersComposeCalculatorTest {

    @Test
    public void calculate() {
        Set<DigitCompose> digitComposeSet = new HashSet<>();

        List<Integer> digitList = new LinkedList<>();
        digitList.add(1);
        DigitCompose digitCompose_1 = new DigitCompose(digitList);
        digitComposeSet.add(digitCompose_1);

        digitList.add(2);
        DigitCompose digitCompose_12 = new DigitCompose(digitList);
        digitComposeSet.add(digitCompose_12);

        DigitCompose digitCompose_2 = new DigitCompose(2);
        digitComposeSet.add(digitCompose_2);

        MyParallelLettersComposeCalculator myParallelLettersComposeCalculator = new MyParallelLettersComposeCalculator();
        Map<DigitCompose, Set> map = myParallelLettersComposeCalculator.calculate(digitComposeSet);

        Assert.assertTrue(map.get(digitCompose_1) == null);

        Set letterComposes = map.get(digitCompose_2);
        Assert.assertTrue(letterComposes != null);

        Assert.assertTrue(map.get(digitCompose_12) == null);
    }
}