package com.sy.sql;

import org.apache.ibatis.jdbc.SQL;

import com.cck.Notify;

/**
 *
 * @author cck
 */
public class NotifySql {

    final static String TABLE_NAME = "t_notify";
    
    /**
     * 增加日记记录
     * @param diray
     * @return
     */
    public String save(Notify notify) {
        
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                INTO_COLUMNS("user_id, msg, topic_id");
                INTO_VALUES( "#{userId}, #{msg}, #{topicId}");
            }
        }.toString(); 
    }
    
    public String get(Integer userId) {
        
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                WHERE("user_id = #{userId}");
                AND();
                WHERE("is_read = 0");
            }
        }.toString();
    }
    
    public String updateRead(Integer id) {
        
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                SET("is_read = 1");
                WHERE("id = #{id}");
            }
        }.toString();
    }
    
    public String getNoReadNum(Integer userId) {
        
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(TABLE_NAME);
                WHERE("user_id = #{userId}");
                AND();
                WHERE("is_read = 0");
            }
        }.toString();
    }
}
