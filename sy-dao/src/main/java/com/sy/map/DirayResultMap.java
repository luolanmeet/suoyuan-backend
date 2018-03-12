package com.sy.map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cck.Diray;

/**
 *
 * @author cck
 */
public interface DirayResultMap {
	
	@Results(id = "map", value = {
		@Result(property = "id",        column = "id"),
		@Result(property = "userId",    column = "user_id"),
		@Result(property = "content",   column = "content"),
		@Result(property = "writeTime", column = "write_time")
	})
	@Select("")
	Diray resultMap();
	
}
