package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.cck.Notify;
import com.sy.map.NotifyResultMap;
import com.sy.sql.NotifySql;

/**
 *
 * @author cck
 */
public interface NotifyMapper extends NotifyResultMap {
    
    @InsertProvider(type = NotifySql.class, method = "save")
    int save(Notify notify);
    
    @ResultMap("map")
    @SelectProvider(type = NotifySql.class, method = "get")
    List<Notify> get(Integer userId);
    
    @UpdateProvider(type = NotifySql.class, method = "updateRead")
    void updateRead(Integer id);
    
    @SelectProvider(type = NotifySql.class, method = "getNoReadNum")
    Integer getNoReadNum(Integer userId);
}
