package com.sy.emotion.judge;

import java.util.List;

import com.sy.emotion.object.Emotions;

/**
 * 
 * @author cck
 */
public interface IJudge {
    
    /**
     * 获取情感详情
     * @param words
     * @return Emotions 
     */
    Emotions judge(List<String> words);
}
