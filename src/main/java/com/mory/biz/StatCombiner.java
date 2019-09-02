package com.mory.biz;

import com.mory.entity.DigitCompose;
import com.mory.interfaces.DigitsComposeCalculator;
import com.mory.interfaces.LettersComposeCalculator;

import java.util.*;

/**
 * 统计总启动类<br/>
 * 算法过程:<br/>
 * 1. 先找出用户输入的数字数组的所有子数组,<br/>
 * 2. 对各数字子数组计算所有的字母组合,<br/>
 */
public class StatCombiner {

    /**
     * 数字组合计算器
     */
    private DigitsComposeCalculator digitsComposeCalculator;

    /**
     * 字母组合计算器
     */
    private LettersComposeCalculator lettersComposeCalculator;

    public StatCombiner(DigitsComposeCalculator digitsComposeCalculator, LettersComposeCalculator lettersComposeCalculator){
        this.digitsComposeCalculator =  digitsComposeCalculator;
        this.lettersComposeCalculator = lettersComposeCalculator;
    }

    /**
     * 统计开始
     * @param digitsList
     * @throws Exception
     */
    public ResultCompose startCount(List<Integer> digitsList) throws Exception {
        Set<DigitCompose> digitComposeSet = this.digitsComposeCalculator.calculate(digitsList);

        Map<DigitCompose, Set> composeResultMap = this.lettersComposeCalculator.calculate(digitComposeSet);

        return new ResultCompose(digitComposeSet, composeResultMap);
    }
}
