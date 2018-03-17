package com.object.code;

/**
 * 系统错误码
 * @author cck
 */
public interface ErrorCode {
	
	/** 成功 */
	int OK = 0;
	
	/** 服务器内部错误	 */
	int INTERNAL_ERROR = 500;
	
	/** 邮箱已被注册 */
	int EMAIL_HAS_REGISTER = 1000;
	
	/** 用户未登录 */
	int USER_NO_LOGIN = 1002;	
	
	/** 用户不存在 */
	int USER_NO_EXITS = 1004;	
}
