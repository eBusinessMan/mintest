package com.mory.interfaces.impl;

import com.mory.entity.DigitCompose;
import com.mory.interfaces.DigitsComposeCalculator;
import com.mory.utils.DigitComposeUtils;

import java.util.*;

public class MyDigitsComposeCalculator implements DigitsComposeCalculator<Integer, DigitCompose> {
    @Override
    public Set<DigitCompose> calculate(List<Integer> digitsList) {
        if (digitsList == null || digitsList.isEmpty()) {
            return null;
        }

        HashSet<DigitCompose> digitComposeSet = new HashSet<>();
        digitComposeSet.add(new DigitCompose(digitsList.get(0)));

        for (int i = 1; i < digitsList.size(); i++) {
            Iterator<DigitCompose> digitComposeIterator = digitComposeSet.iterator();
            List<DigitCompose> tmpList = new LinkedList<>();

            Integer x = digitsList.get(i);
            while (digitComposeIterator.hasNext()) {
                DigitCompose digitCompose = digitComposeIterator.next();
                tmpList.add(DigitComposeUtils.compose(digitCompose, x));
            }
            tmpList.add(new DigitCompose(x));

            digitComposeSet.addAll(tmpList);
        }

        return digitComposeSet;
    }
}
