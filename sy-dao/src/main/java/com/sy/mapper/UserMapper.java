package com.sy.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.cck.User;
import com.sy.map.UserResultMap;
import com.sy.sql.UserSql;

/**
 *
 * @author cck
 */
public interface UserMapper extends UserResultMap {
	
	@InsertProvider(type = UserSql.class, method = "save")
	int save(User user);
	
	@UpdateProvider(type = UserSql.class, method = "update")
	int update(User user);
	
	@UpdateProvider(type = UserSql.class, method = "updateLastDirayDate")
	int updateLastDirayDate(@Param("id")Integer id, @Param("date")String date);
	
	@ResultMap("map")
	@SelectProvider(type = UserSql.class, method = "getById")
	User getById(Integer id);
	
	@ResultMap("map")
	@SelectProvider(type = UserSql.class, method = "getByEmailAndPwd")
	User getByEmailAndPwd(@Param("email")String email, @Param("pwd")String pwd);
	
	@SelectProvider(type = UserSql.class, method = "isExistEmail")
	int isExistEmail(String email);
	
}
