package com.mory.entity;

import com.mory.utils.DigitComposeUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 子数字组合包装类<br/>
 * tips; 由于本测试题场景中需要, 成员属性digits相等(equals)的不同DigitCompose实例相等(equals), <br/>
 * 故同时覆盖了Object实例的hashCode(), equals()方法.<br/>
 * 额外地, 也重写了, toString()方法.
 */
@Getter
public class DigitCompose {

    /**
     * 数字组合字符串, 初始值是""
     */
    private final String digits;
    /**
     * 标识是否包含[2..9]之外的数字元素
     */
    private final boolean containByondLetter;

    public DigitCompose(Integer i) {
        if (i != null) {
            this.digits = i.toString();
            containByondLetter = DigitComposeUtils.checkIfContainBeyondLetter(Arrays.asList(i));

            return;
        }

        this.digits = "";
        containByondLetter = true;
    }

    public DigitCompose(List<Integer> digitList) {
        this.digits = this.convertDigitList(digitList);
        this.containByondLetter = DigitComposeUtils.checkIfContainBeyondLetter(digitList);
    }

    /**
     * 按照DigitSorter输出序列
     *
     * @return
     */
    protected String convertDigitList(List<Integer> digitList) {
        if (digitList == null || digitList.isEmpty()) {
            return "";// digits 不会为null
        }

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < digitList.size(); i++) {
            Integer tmp = digitList.get(i);
            strBuilder.append(tmp);
            strBuilder.append(',');
        }

        strBuilder.deleteCharAt(strBuilder.length() - 1);
        return strBuilder.toString();
    }

    @Override
    public int hashCode() {
        return digits.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj instanceof DigitCompose) {
            String aDigits = ((DigitCompose) obj).getDigits();
            return this.getDigits().equals(aDigits); // 由上面代码知,digits 不会为null
        }

        return false;
    }

    @Override
    public String toString() {
        return getDigits();
    }
}
