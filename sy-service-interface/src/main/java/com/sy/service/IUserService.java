package com.sy.service;

import com.cck.User;
import com.object.exception.ErrorCodeException;

/**
 *
 * @author cck
 */
public interface IUserService {
	
	Integer login(String email, String pwd) throws ErrorCodeException;
	Integer register(String email, String pwd) throws ErrorCodeException;
	Integer set(User user);
}
