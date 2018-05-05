package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import com.cck.Diray;
import com.sy.map.DirayResultMap;
import com.sy.sql.DiraySql;

/**
 *
 * @author cck
 */
public interface DirayMapper extends DirayResultMap {

    @InsertProvider(type = DiraySql.class, method = "save")
    int save(Diray diray);

    @ResultMap("map")
    @SelectProvider(type = DiraySql.class, method = "getByUserId")
    List<Diray> getByUserId(Integer userId);

    @ResultMap("map")
    @SelectProvider(type = DiraySql.class, method = "getByWriteTime")
    List<Diray> getByWriteTime(
            @Param("userId")Integer userId, 
            @Param("dateTime")String dateTime);
}
