package com.sy.emotion.judge.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sy.emotion.object.Emotion;
import com.sy.emotion.object.Emotions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmotionJudge extends AbstractJudge {

    @Override
    public Emotions judge(List<String> words) {
        
        // 取出情感词
        // 取出反转词
        // 取出程度词
        // 情感值 =  反转词 * 程度词 * 情感词
        Emotions emotions = new Emotions();
            
        for (int i = 0; i < words.size(); i++) {
            
            // 往情感词前一个或两个查找反转词与程度词
            Double wordVal = EMOTION_WORDS.get(words.get(i));
            if (wordVal == null) {      
                continue;
            }
            Double temWordVal = wordVal;
            
            StringBuilder sentence = new StringBuilder();
            StringBuilder sentenceDetail = new StringBuilder();
            // 往前查找两个或一个
            if (i > 2) {
                
                String temWord1 = words.get(i-1);
                
                // 如果前一个词是也是情感词，则不需要判断
                if (!EMOTION_WORDS.containsKey(temWord1)) {
                    
                    String temWord2 = words.get(i-2);
                    temWordVal = handleWord(temWordVal, sentence, sentenceDetail, temWord2);
                    temWordVal = handleWord(temWordVal, sentence, sentenceDetail, temWord1);
                }
            } else if (i > 1) {
                
                String temWord = words.get(i-1);
                temWordVal = handleWord(temWordVal, sentence, sentenceDetail, temWord);
            }
            
            sentence.append(words.get(i));
            sentenceDetail.append(EMOTION_WORD)
                          .append(":")
                          .append(words.get(i))
                          .append(":")
                          .append(wordVal);
            
            Emotion emotion = Emotion.builder()
                    .emotionSentence(sentence.toString())
                    .emotionSentenceVal(temWordVal)
                    .emotionSentenceValDetail(sentenceDetail.toString())
                    .build();
            emotions.addSentence(emotion, temWordVal > 0);
        }
        
        return emotions;
    }

    private Double handleWord(Double val, 
            StringBuilder sentence, 
            StringBuilder sentenceDetail,
            String word) {
        
        int type = judgeDenyOrDegreeWord(word);
        if (type == DEGREE_WORD) {
            
            Double degreeVal = DEGREE_WORDS.get(word);
            val *= degreeVal;
            sentence.append(word);
            sentenceDetail.append(DEGREE_WORD)
                          .append(":")
                          .append(word)
                          .append(":")
                          .append(degreeVal)
                          .append(",");
        } else if (type == DENY_WORD) {
            
            val *= -1;
            sentence.append(word);
            sentenceDetail.append(DENY_WORD)
                          .append(":")
                          .append(word)
                          .append(":")
                          .append(-1)
                          .append(",");
        }
           
        BigDecimal temVal = new BigDecimal(val);
        val = temVal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return val;
    }
    
    public static void main(String[] args) {
        
        EmotionJudge judge = new EmotionJudge();
        List<String> list = 
                Arrays.asList("哈哈哈", "今天", "也是", "非常", "开心", "的", "一", "天", "呐");
        Emotions emotions = judge.judge(list);
        log.info("{}", emotions);
    }
    
}
