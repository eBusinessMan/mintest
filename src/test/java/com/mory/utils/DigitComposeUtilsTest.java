package com.mory.utils;

import com.mory.entity.DigitCompose;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class DigitComposeUtilsTest {

    @Test
    public void checkIfContainBeyondLetter() {
        List<Integer> digitList0 = new LinkedList<>();
        digitList0.add(1);
        digitList0.add(2);
        DigitCompose digitCompose0 = new DigitCompose(digitList0);
        Assert.assertTrue(DigitComposeUtils.checkIfContainBeyondLetter(digitList0) == true);

        List<Integer> digitList1 = new LinkedList<>();
        digitList1.add(3);
        digitList1.add(4);
        DigitCompose digitCompose1 = new DigitCompose(digitList1);
        Assert.assertTrue(DigitComposeUtils.checkIfContainBeyondLetter(digitList1) == false );
    }

    @Test
    public void compose() {
        List<Integer> digitList0 = new LinkedList<>();
        digitList0.add(1);
        digitList0.add(2);
        DigitCompose digitCompose0 = new DigitCompose(digitList0);

        digitList0.add(3);
        DigitCompose digitCompose1 = new DigitCompose(digitList0);

        Assert.assertTrue(digitCompose1.equals(DigitComposeUtils.compose(digitCompose0, 3)));
    }
}