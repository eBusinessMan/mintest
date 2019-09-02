package com.mory.interfaces.impl;

import com.mory.common.CombinationContext;
import com.mory.entity.DigitCompose;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并行
 */
public class MyParallelLettersComposeCalculator extends ParallelLettersComposeCalculator<DigitCompose> {

    public void doCalculate(ConcurrentHashMap<DigitCompose, Set> dlMap, DigitCompose digitCompose) {
        //如果包含超出[2,9]的数字, 则会与其他digitCompose的字母组合重复, 故直接不处理
        if (digitCompose.isContainByondLetter()) {
            return;
        }

        try {
            HashSet<String> letterComposeSet = new HashSet<String>();
            dlMap.put(digitCompose, letterComposeSet);

            String tmpDigits = digitCompose.getDigits();

            //单个数字字符
            if (tmpDigits.length() == 1) {
                String[] lettersFirst = CombinationContext.digitLetterMapping.get(tmpDigits);
                for (String l : lettersFirst) {
                    letterComposeSet.add(l);
                }

                return;
            }

            //多个数字字符组合
            String[] digitsArray = digitCompose.getDigits().split(",");

            int i = 0;
            String[] lettersFirst = CombinationContext.digitLetterMapping.get(digitsArray[i]);
            List<String> tmpList = Arrays.asList(lettersFirst);
            for (i = 1; i < digitsArray.length; i++) {
                String[] lettersNext = CombinationContext.digitLetterMapping.get(digitsArray[i]);
                tmpList = this.composeLetters(tmpList, lettersNext);
            }

            //对字母组合去重, 如BCE, BEC, EBC这类应该去重
            for (String letterCompose : tmpList) {
                char[] letterChars = letterCompose.toCharArray();
                Arrays.sort(letterChars);//字母从小到大排序

                letterComposeSet.add(new String(letterChars));
            }

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 两个数组的元素的两两组合
     *
     * @param first
     * @param next
     * @return
     */
    private List<String> composeLetters(List<String> first, String[] next) {
        if ((first == null || first.isEmpty()) || next == null) {
            return null;
        }

        List<String> tmpList = new LinkedList<>();
        for (String a : first) {
            for (String b : next) {
                tmpList.add(a + b);
            }
        }

        return tmpList;
    }
}
