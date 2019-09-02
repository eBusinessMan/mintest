package com.mory.interfaces;

import java.util.List;
import java.util.Set;

/**
 * 用户输入的数字数组的子数组计算器, 其中各个子数组会被转化成V实例.
 * @param <T>
 * @param <V>
 */
public interface DigitsComposeCalculator<T, V> {

    Set<V> calculate(List<T> digitsList);

}
