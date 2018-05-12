package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.cck.Reply;
import com.sy.map.ReplyResultMap;
import com.sy.sql.ReplySql;

/**
 *
 * @author cck
 */
public interface ReplyMapper extends ReplyResultMap {

    @InsertProvider(type = ReplySql.class, method = "save")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Reply reply);
    
    @UpdateProvider(type = ReplySql.class, method = "updatePath")
    void updatePath(
            @Param("id")Integer id, 
            @Param("path")String path);
    
    @ResultMap("map")
    @SelectProvider(type = ReplySql.class, method = "getByTopicId")
    List<Reply> getByTopicId(Integer topicId);
    
    @ResultMap("map")
    @SelectProvider(type = ReplySql.class, method = "getByPath")
    List<Reply> getByPath(String path);
    
    @SelectProvider(type = ReplySql.class, method = "getPath")
    String getPath(Integer id);
    
}
