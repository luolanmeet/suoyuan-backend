package com.sy.emotion.object;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一篇文章的情感
 * @author cck
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emotions {
    
    /** 情感值 */
    private Double emotionVal = 0D;
    /** 积极情感值 */
    private Double poEmotionVal = 0D;
    /** 消极情感值 */
    private Double neEmotionVal = 0D;
    /** 积极句子数量 */
    private Integer poCount = 0;
    /** 消极句子数量 */
    private Integer neCount = 0;
    /** 积极句子*/
    private List<Emotion> poEmotions = new ArrayList<>(5);
    /** 消极句子 */
    private List<Emotion> neEmotions = new ArrayList<>(5);
    
    public void addSentence(Emotion sentence, Boolean isPoSentence) {
        
        if (isPoSentence) {
            poEmotions.add(sentence);
            poCount++;
            poEmotionVal += sentence.getEmotionSentenceVal();
        } else {
            neEmotions.add(sentence);
            neCount++;
            neEmotionVal += sentence.getEmotionSentenceVal();
        }
        emotionVal += sentence.getEmotionSentenceVal();
    }
}
