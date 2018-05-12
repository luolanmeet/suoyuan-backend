package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.Reply;

/**
 *
 * @author cck
 */
public interface ReplyResultMap {

    @Results(id = "map", value = {
        @Result(property = "id",              column = "id"),
        @Result(property = "topicId",         column = "topic_id"),
        @Result(property = "fromUserId",      column = "from_user_id"),
        @Result(property = "nickname",        column = "nickname"),
        @Result(property = "avator",          column = "avator"),
        @Result(property = "toUserId",        column = "to_user_id"),
        @Result(property = "toReplyId",       column = "to_reply_id"),
        @Result(property = "content",         column = "content"),
        @Result(property = "writeTime",       column = "write_time"),
        @Result(property = "path",            column = "path"),
    })
    @Select("")
    Reply resultMap();
}
