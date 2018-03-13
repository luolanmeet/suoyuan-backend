package com.sy.service;

import com.cck.User;
import com.object.exception.ErrorCodeException;

/**
 *
 * @author cck
 */
public interface IUserService {
	
	Integer login(String email, String pwd) throws ErrorCodeException;
	Integer register(User user);
	Integer set(User user);
}
