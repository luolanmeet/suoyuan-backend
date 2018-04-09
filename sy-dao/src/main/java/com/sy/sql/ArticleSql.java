package com.sy.sql;

import org.apache.ibatis.jdbc.SQL;

import com.cck.Article;

/**
 *
 * @author cck
 */
public class ArticleSql {

    final static String TABLE_NAME = "t_article";
    
    /**
     * 新增文章
     * @param article
     * @return
     */
    public String save(Article article) {
        
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                INTO_COLUMNS("url, title, author, content, emotion_val");
                INTO_VALUES("#{url}, #{title}, #{author}, #{content}, #{emotionVal}");
            }
        }.toString();
    }

    /** 获取公开日志 */
    public String getArticle(Double nowEmotion) {

        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" ORDER BY ABS(emotion_val - #{nowEmotion})")
                .append(" DESC LIMIT 1");

        return sql.toString();
    }

}
