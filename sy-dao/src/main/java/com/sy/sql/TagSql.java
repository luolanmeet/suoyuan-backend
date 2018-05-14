package com.sy.sql;

import org.apache.ibatis.annotations.Param;

import com.sy.mapper.TagMapper;

/**
 *
 * @author cck
 */
public class TagSql {

    final static String TABLE_NAME = "t_tag";
    final static String DUAL_TABLE = "dual";
    
    /**
     * 增加日记记录
     * @param diray
     * @return
     */
    public String save(
            @Param("createrId")Integer createrId, 
            @Param("name")String name) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO ")
           .append(TABLE_NAME)
           .append(" (creater_id, name) ")
           .append(" SELECT #{createrId}, #{name} FROM ")
           .append(DUAL_TABLE)
           .append(" WHERE NOT EXISTS (SELECT 1 FROM ")
           .append(TABLE_NAME)
           .append(" WHERE name = #{name})");
        
        return sql.toString();
    }
    
    public String get() {
        
        return "SELECT * FROM "
                +   TABLE_NAME
                + " ORDER BY topic_num DESC "
                + " LIMIT "
                +   TagMapper.MAX_TAG_NUM;
    }
}
