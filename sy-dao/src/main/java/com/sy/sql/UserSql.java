package com.sy.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.cck.User;
import com.sy.mapper.UserMapper;

/**
 *
 * @author cck
 */
public class UserSql {

    final static String TABLE_NAME = "t_user";
    final static String DUAL_TABLE = "dual";

    /**
     * 增加用户
     * @param user
     * @return
     */
    public String save(User user) {

        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO ")
                .append(TABLE_NAME)
                .append(" (email, PASSWORD, nickname, avator) ")
                .append("SELECT #{email}, #{password}, #{nickname}, #{avator} FROM ")
                .append(DUAL_TABLE)
                .append(" WHERE NOT EXISTS (SELECT 1 FROM ")
                .append(TABLE_NAME)
                .append(" WHERE email = #{email} LIMIT 1)");

        return sql.toString();
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public String getById(Integer id) {

        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                WHERE("id = #{id}");
            }
        }.toString();
    }

    /**
     * 根据邮箱密码查询用户
     * @param id
     * @return
     */
    public String getByEmailAndPwd(@Param("email")String email, @Param("pwd")String pwd) {

        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                WHERE("email = #{email}");
                AND();
                WHERE("password = #{pwd}");
            }
        }.toString();
    }

    /**
     * 查看邮箱是否存在
     * @param email
     * @return 0:存在 1:不存在
     */
    public String isExistEmail(String email) {

        StringBuilder sql = new StringBuilder()
            .append("SELECT ISNULL((SELECT 1 FROM ")
            .append(TABLE_NAME)
            .append(" WHERE email = #{email} LIMIT 1))");

        return sql.toString();
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public String update(User user) {
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if(user.getEmail() != null && !("").equals(user.getEmail())) {
                    SET("email = #{email}");
                }
                if(user.getPassword() != null && !("").equals(user.getPassword())) {
                    SET("password = #{password}");
                }
                if(user.getNickname() != null && !("").equals(user.getNickname())) {
                    SET("nickname = #{nickname}");
                }
                if(user.getAvator() != null && !("").equals(user.getAvator())) {
                    SET("avator = #{avator}");
                }
                if(user.getSignature() != null && !("").equals(user.getSignature())) {
                    SET("signature = #{signature}");
                }
                if(user.getIsOpen() != null) {
                    SET("is_open = #{isOpen}");
                }
                if(user.getDirayCount() != null) {
                    SET("diray_count = #{dirayCount}");
                }
                if(user.getLastDirayDate() != null) {
                    SET("last_diray_date = #{lastDirayDate}");
                }
                if(user.getNowEmotion() != null) {
                    SET("now_emotion = #{nowEmotion}");
                }
                if(user.getLastDirayEmotion() != null) {
                    SET("last_diray_emotion = #{lastDirayEmotion}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

    /** 设置用户最后编写日志的日期与情感值 */
    public String updateLastDirayDateAndEmotion(
            @Param("id")Integer userId, 
            @Param("date")String date, 
            @Param("nowEmotion")Double nowEmotion, 
            @Param("lastDirayEmotion")Double lastDirayEmotion, 
            @Param("lastLoginDate")String lastLoginDate) {
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                SET("last_diray_date = #{date}");
                SET("now_emotion = #{nowEmotion}");
                SET("last_diray_emotion = #{lastDirayEmotion}");
                SET("last_login_date = #{lastLoginDate}");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    /** 获取首页展示的头像 */
    public String getRandomPic() {

        return "SELECT avator FROM "
                +   TABLE_NAME
                + " ORDER BY last_diray_date DESC LIMIT "
                +   UserMapper.INDEX_NEED_PIC_NUM;
    }

    /** 获取公开日志 */
    public String getOpenDirayUser() {

        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE is_open = 1 LIMIT 40");

        return sql.toString();
    }

}
