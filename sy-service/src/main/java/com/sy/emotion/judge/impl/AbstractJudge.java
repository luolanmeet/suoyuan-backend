package com.sy.emotion.judge.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sy.emotion.judge.IJudge;
import com.sy.word.util.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cck
 */
@Slf4j
public abstract class AbstractJudge implements IJudge {
    
    private static final String DENY_WORD_PATH    = "classpath:deny";
    private static final String DEGREE_WORD_PATH  = "classpath:degree";
    private static final String EMOTION_WORD_PATH = "classpath:emotion_word";
    
    public final static int OTHTERS      = 0;
    public final static int DENY_WORD    = 1;
    public final static int DEGREE_WORD  = 2;
    public final static int EMOTION_WORD = 3;
    
    /** 否定词 */
    protected static Set<String> DENY_WORDS = new HashSet<>();
    /** 程度词 */
    protected static Map<String, Double> DEGREE_WORDS = new HashMap<>();
    /** 情感词 */
    protected static Map<String, Double> EMOTION_WORDS = new HashMap<>();
    
    static {
        init();
    }
    
    private static void init() {
        
        List<String> denyWordList = ResourceLoader.load(DENY_WORD_PATH);
        buildSet(DENY_WORDS, denyWordList);
        List<String> emotionWordList = ResourceLoader.load(EMOTION_WORD_PATH);
        buildMap(EMOTION_WORDS, emotionWordList);
        List<String> degreeWordList = ResourceLoader.load(DEGREE_WORD_PATH);
        buildMap(DEGREE_WORDS, degreeWordList);
    }
    
    private static void buildSet(Set<String> set, List<String> wordList) {
        
        for (String word : wordList) {
            DENY_WORDS.add(word);
        }
    }

    private static void buildMap(Map<String, Double> map, List<String> wordList) {
        
        for (String word : wordList) {
            String[] split = word.split(":");
            
            BigDecimal temVal = new BigDecimal(Double.valueOf(split[1]));
            Double val = temVal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put(split[0], val);
        }
    }
    
    /** 判断是否是程度词或是反转词 */
    protected static int judgeDenyOrDegreeWord(String word) {
        
        if (DENY_WORDS.contains(word)) {
            return DENY_WORD;
        }
        
        if (DEGREE_WORDS.containsKey(word)) {
            return DEGREE_WORD;
        }
        
        return OTHTERS;
    }
    
    public static void main(String[] args) {
        log.info("{}", DENY_WORDS);
        log.info("{}", EMOTION_WORDS);
        log.info("{}", DEGREE_WORDS);
    }
    
}
