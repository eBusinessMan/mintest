package com.mory.interfaces;

import java.util.Map;
import java.util.Set;

/**
 * 字母组合计算器
 * @param <T>
 */
public interface LettersComposeCalculator<T> {
    /**
     * 逐个计算set中的各元素的对应的字母组合
     * @param set
     * @return
     * @throws Exception
     */
    Map<T, Set> calculate(Set<T> set) throws Exception;
}
