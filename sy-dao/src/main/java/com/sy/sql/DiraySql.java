package com.sy.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.cck.Diray;

/**
 *
 * @author cck
 */
public class DiraySql {

    final static String TABLE_NAME = "t_diray";

    /**
     * 增加日记记录
     * @param diray
     * @return
     */
    public String save(Diray diray) {

        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                INTO_COLUMNS("user_id, content, write_time, "
                           + "emotion_val, po_emotion_val, "
                           + "ne_emotionVal, po_count, "
                           + "ne_count, po_sentences, "
                           + "ne_sentences, po_sentence_details, "
                           + "ne_sentence_details");
                INTO_VALUES( "#{userId}, #{content}, now(), "
                           + "#{emotionVal}, #{poEmotionVal}, "
                           + "#{neEmotionVal}, #{poCount}, "
                           + "#{neCount}, #{poSentences}, "
                           + "#{neSentences}, #{poSentenceDetails}, "
                           + "#{neSentenceDetails}");
            }
        }.toString(); 
    }

    /**
     * 根据用户id获取日记
     * @param userId
     * @return
     */
    public String getByUserId(Integer userId) {

        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                WHERE("user_id = #{userId}");
                ORDER_BY("write_time DESC");
            }
        }.toString();
    }

    /**
     * 获取指定个日期内的所有日记
     * @param dateTime
     * @return
     */
    public String getByWriteTime(
            @Param("userId")Integer userId,
            @Param("dateTime")String dateTime) {

        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                WHERE(getTimeInterval(dateTime));
                AND();
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }

    private String getTimeInterval(String dateTime) {
        return new StringBuilder()
                .append("write_time")
                .append(" BETWEEN '")
                .append(dateTime)
                .append(" 00:00:00'")
                .append(" AND '")
                .append(dateTime)
                .append(" 23:59:59'")
                .toString();
    }

    public void getLastDiray(@Param("userId")Integer userId) {

    }
}
