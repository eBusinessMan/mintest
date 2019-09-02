package com.mory.biz;

import com.mory.entity.DigitCompose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
public class ResultCompose {
    private Set<DigitCompose> digitComposeSet;
    private Map<DigitCompose, Set> composeResultMap;
}
