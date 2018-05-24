package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.Notify;

/**
 *
 * @author cck
 */
public interface NotifyResultMap {

    @Results(id = "map", value = {
        @Result(property = "id",              column = "id"),
        @Result(property = "userId",          column = "user_id"),
        @Result(property = "msg",             column = "msg"),
        @Result(property = "topicId",         column = "topic_id"),
        @Result(property = "isRead",          column = "is_read"),
    })
    @Select("")
    Notify resultMap();
}
