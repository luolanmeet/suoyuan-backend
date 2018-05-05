package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.Diray;

/**
 *
 * @author cck
 */
public interface DirayResultMap {

    @Results(id = "map", value = {
        @Result(property = "id",                column = "id"),
        @Result(property = "userId",            column = "user_id"),
        @Result(property = "content",           column = "content"),
        @Result(property = "writeTime",         column = "write_time"),
                                                
        @Result(property = "emotionVal",        column = "emotion_val"),
        @Result(property = "poEmotionVal",      column = "po_emotion_val"),
        @Result(property = "neEmotionVal",      column = "ne_emotionVal"),
        @Result(property = "poCount",           column = "po_count"),
        @Result(property = "neCount",           column = "ne_count"),
        @Result(property = "poSentences",       column = "po_sentences"),
        @Result(property = "neSentences",       column = "ne_sentences"),
        @Result(property = "poSentenceDetails", column = "po_sentence_details"),
        @Result(property = "neSentenceDetails", column = "ne_sentence_details"),
    })
    @Select("")
    Diray resultMap();
}
