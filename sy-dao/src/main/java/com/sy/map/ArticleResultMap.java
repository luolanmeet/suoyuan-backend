package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.Article;

/**
 *
 * @author cck
 */
public interface ArticleResultMap {

    @Results(id = "map", value = {
        @Result(property = "id",         column = "id"),
        @Result(property = "url",        column = "url"),
        @Result(property = "title",      column = "title"),
        @Result(property = "author",     column = "author"),
        @Result(property = "content",    column = "content"),
        @Result(property = "emotionVal", column = "emotion_val")
    })
    @Select("")
    Article article();
}
