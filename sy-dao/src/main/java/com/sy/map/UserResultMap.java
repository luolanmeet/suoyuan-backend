package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
		@Result(property = "lastDirayDate", column = "last_diray_date") 
	})
	@Select("")
	User resultMap();

}
