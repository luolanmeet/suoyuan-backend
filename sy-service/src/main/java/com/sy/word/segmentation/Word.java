package com.sy.word.segmentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 词
 * @author cck
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    private String text;
    // 需要加上情绪值？？
}
