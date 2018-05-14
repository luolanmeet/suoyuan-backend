package com.sy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.cck.OpenDirayUser;
import com.cck.User;
import com.sy.map.UserResultMap;
import com.sy.sql.UserSql;

/**
 *
 * @author cck
 */
public interface UserMapper extends UserResultMap {
    
    /**
     * 首页需要的图片数量
     */
    int INDEX_NEED_PIC_NUM = 5;

    @InsertProvider(type = UserSql.class, method = "save")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(User user);

    @UpdateProvider(type = UserSql.class, method = "update")
    int update(User user);

    @UpdateProvider(type = UserSql.class, 
            method = "updateLastDirayDateAndEmotion")
    int updateLastDirayDateAndEmotion(
            @Param("id")Integer id, 
            @Param("date")String date, 
            @Param("nowEmotion")Double nowEmotion, 
            @Param("lastDirayEmotion")Double lastDirayEmotion, 
            @Param("lastLoginDate")String lastLoginDate);

    @ResultMap("map")
    @SelectProvider(type = UserSql.class, method = "getById")
    User getById(Integer id);

    @ResultMap("map")
    @SelectProvider(type = UserSql.class, method = "getByEmailAndPwd")
    User getByEmailAndPwd(
            @Param("email")String email, 
            @Param("pwd")String pwd);

    @SelectProvider(type = UserSql.class, method = "isExistEmail")
    int isExistEmail(String email);

    @SelectProvider(type = UserSql.class, method = "getRandomPic")
    List<String> getRandomPic();

    @ResultMap("openDirayUserMap")
    @SelectProvider(type = UserSql.class, method = "getOpenDirayUser")
    List<OpenDirayUser> getOpenDirayUser(Double nowEmotion);
    
    @SelectProvider(type = UserSql.class, method = "getAvator")
    List<String> getAvator(@Param("userIds")List<Integer> userIds);
}
