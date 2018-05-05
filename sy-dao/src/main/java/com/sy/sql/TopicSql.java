package com.sy.sql;

import org.apache.ibatis.jdbc.SQL;

import com.cck.Topic;

/**
 *
 * @author cck
 */
public class TopicSql {

    final static String TABLE_NAME = "t_topic";

    /**
     * 增加日记记录
     * @param diray
     * @return
     */
    public String save(Topic topic) {

        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                INTO_COLUMNS("user_id, title, tag, "
                           + "content, write_time");
                INTO_VALUES( "#{userId}, #{title}, #{tag}, "
                           + "#{content}, now()");
            }
        }.toString(); 
    }
    
    public String get() {
        
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
            }
        }.toString();
    }
}
