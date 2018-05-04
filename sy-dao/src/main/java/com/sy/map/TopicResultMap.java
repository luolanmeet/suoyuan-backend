package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.Topic;

/**
 *
 * @author cck
 */
public interface TopicResultMap {

    @Results(id = "map", value = {
        @Result(property = "id",                column = "id"),
        @Result(property = "userId",            column = "user_id"),
        @Result(property = "title",             column = "title"),
        @Result(property = "tag",               column = "tag"),
        @Result(property = "content",           column = "content"),
        @Result(property = "writeTime",         column = "write_time"),
        @Result(property = "replyNum",          column = "reply_num"),
    })
    @Select("")
    Topic resultMap();

}
