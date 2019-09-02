package com.mory.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 组合需要的基本元素
 */
public class CombinationContext {
    /**
     * 测试题目中的数字和字母的映射, digitLetterMapping是一个不可变map
     */
    public static final Map<String, String[]> digitLetterMapping = Collections.unmodifiableMap(new HashMap<String, String[]>() {
        {
            put("2", new String[]{"A", "B", "C"});
            put("3", new String[]{"D", "E", "F"});
            put("4", new String[]{"G", "H", "I"});
            put("5", new String[]{"J", "K", "L"});
            put("6", new String[]{"M", "N", "O"});
            put("7", new String[]{"P", "Q", "R", "S"});
            put("8", new String[]{"T", "U", "V"});
            put("9", new String[]{"W", "X", "Y", "Z"});
        }
    });

}
