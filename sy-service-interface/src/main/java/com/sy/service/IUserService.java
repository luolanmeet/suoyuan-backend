package com.sy.service;

import com.cck.User;
import com.object.exception.ErrorCodeException;

/**
 *
 * @author cck
 */
public interface IUserService {
	
	void login(String email, String pwd) throws ErrorCodeException;
	void register(String email, String pwd, String nickname) throws ErrorCodeException;
	void set(User user);
}
