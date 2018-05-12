package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.OpenDirayUser;
import com.cck.User;

/**
 *
 * @author cck
 */
public interface UserResultMap {

    @Results(id = "map", value = {
        @Result(property = "id",            column = "id"),
        @Result(property = "email",         column = "email"),
        @Result(property = "password",      column = "password"),
        @Result(property = "nickname",      column = "nickname"),
        @Result(property = "avator",        column = "avator"),
        @Result(property = "signature",     column = "signature"),
        @Result(property = "isOpen",        column = "is_open"),
        @Result(property = "dirayCount",    column = "diray_count"),
        @Result(property = "lastDirayDate", column = "last_diray_date"),
        
        @Result(property = "nowEmotion",       column = "now_emotion"),
        @Result(property = "lastDirayEmotion", column = "last_diray_emotion"),
        @Result(property = "lastLoginDate",    column = "last_login_date")
    })
    @Select("")
    User user();

    @Results(id = "openDirayUserMap", value = {
        @Result(property = "id",     column = "id"),
        @Result(property = "avator", column = "avator"),
    })
    @Select("")
    OpenDirayUser openDirayUserMap();
}
