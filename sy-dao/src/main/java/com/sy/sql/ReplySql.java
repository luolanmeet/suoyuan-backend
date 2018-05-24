package com.sy.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.cck.Reply;

/**
 *
 * @author cck
 */
public class ReplySql {

    final static String TABLE_NAME = "t_reply";

    /**
     * 增加日记记录
     * @param diray
     * @return
     */
    public String save(Reply reply) {

        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                INTO_COLUMNS("topic_id, title, topic_creater_id, from_user_id, nickname, avator, "
                           + "to_user_id, to_nickname, to_reply_id, content, write_time");
                INTO_VALUES( "#{topicId}, #{title}, #{topicCreaterId}, #{fromUserId}, #{nickname}, #{avator}, "
                           + "#{toUserId}, #{toNickname}, #{toReplyId}, #{content}, now()");
            }
        }.toString(); 
    }

    /**
     * 更新路径
     * @param id
     * @param path
     * @return
     */
    public String updatePath(
            @Param("id")Integer id, 
            @Param("path")String path) {
        
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                SET("path = #{path}");
                WHERE("id = #{id}");
            }
        }.toString();
    }
    
    public String getByTopicId(Integer topicId) {
        
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                WHERE("topic_id = #{topicId}");
            }
        }.toString();
    }
    
    /**
     * 获取当期评论及其所有父节点
     * @param path
     * @return
     */
    public String getByPath(String path) {
        
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                WHERE("#{path} LIKE CONCAT(path, '%')");
            }
        }.toString();
    }
    
    public String getPath(Integer id) {
        
        return new SQL() {
            {
                SELECT("path");
                FROM(TABLE_NAME);
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
