package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.Tag;

/**
 *
 * @author cck
 */
public interface TagResultMap {

    @Results(id = "map", value = {
        @Result(property = "id",                column = "id"),
        @Result(property = "createrId",         column = "creater_id"),
        @Result(property = "name",              column = "name"),
        @Result(property = "topicNum",          column = "topic_num"),
    })
    @Select("")
    Tag resultMap();
}
