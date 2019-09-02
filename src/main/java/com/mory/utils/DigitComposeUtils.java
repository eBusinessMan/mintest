package com.mory.utils;

import com.mory.common.CombinationContext;
import com.mory.entity.DigitCompose;

import java.util.*;

/**
 * 基于DigitCompose 的工具类
 */
public class DigitComposeUtils {

    /**
     * 判断digitList包含是否不在[2,9]之间的数字
     */
    public static boolean checkIfContainBeyondLetter(List<Integer> digitList){
        if (digitList ==null || digitList.isEmpty()) {
            return true;
        }

        for (Integer i : digitList) {
            if (!CombinationContext.digitLetterMapping.containsKey(i.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 基于src, 拼接创建一个全新的DigitCompose实例
     * @param src
     * @param append
     * @return
     */
    public static DigitCompose compose(DigitCompose src, Integer append) {
        List<Integer> appendList = new ArrayList<>(1);
        appendList.add(append);
        return compose(src, appendList);
    }

    /**
     * 基于src, 拼接创建一个全新的DigitCompose实例
     * @param src
     * @param appendList
     * @return
     */
    public static DigitCompose compose(DigitCompose src, List<Integer> appendList) {
        if (appendList == null || src == null)
            throw new RuntimeException("非法入参");

        String[] digitsArray = src.getDigits().split(",");//
        if (!(digitsArray == null || digitsArray.length == 0)) {
            for (int i = 0; i < digitsArray.length; i++) {
                appendList.add(Integer.valueOf(digitsArray[i]));
            }
        }

        appendList.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer so1 = (Integer)o1;
                Integer so2 = (Integer)o2;
                if (so1 > so2) {
                    return 1;
                }
                if (so1 < so2) {
                    return -1;
                }
                return 0;
            }
        });

        DigitCompose target = new DigitCompose(appendList);
        return target;
    }
}
