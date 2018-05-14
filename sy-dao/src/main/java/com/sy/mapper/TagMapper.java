package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import com.cck.Tag;
import com.sy.map.TagResultMap;
import com.sy.sql.TagSql;

/**
 *
 * @author cck
 */
public interface TagMapper extends TagResultMap {

    /**
     * 需要的最多的标签数量
     */
    int MAX_TAG_NUM = 25; 
    
    @InsertProvider(type = TagSql.class, method = "save")
    int save(
            @Param("createrId")Integer createrId, 
            @Param("name")String name);
    
    @ResultMap("map")
    @SelectProvider(type = TagSql.class, method = "get")
    List<Tag> get();
}
