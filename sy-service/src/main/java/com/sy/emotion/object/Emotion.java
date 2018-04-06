package com.sy.emotion.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章情感的基本单位，句子
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emotion {
    
    /**
     * 程度词 + 反转词 + 情感词
     * 反转词 + 程度词 + 情感词
     * 程度词 + 程度词 + 情感词
     * 反转词 + 反转词 + 情感词
     */
    private String emotionSentence;
    
    /**
     * 词性:词:值,词性:词:值,词性:词:值
     */
    private String emotionSentenceValDetail;
    
    /** 情感值 */
    private Double emotionSentenceVal;
}