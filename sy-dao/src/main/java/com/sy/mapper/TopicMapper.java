package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import com.cck.Topic;
import com.sy.map.TopicResultMap;
import com.sy.sql.TopicSql;

/**
 *
 * @author cck
 */
public interface TopicMapper extends TopicResultMap {

    @InsertProvider(type = TopicSql.class, method = "save")
    int save(Topic topic);
    
    @ResultMap("map")
    @SelectProvider(type = TopicSql.class, method = "get")
    List<Topic> get();
}
