package com.sy.service;

import com.cck.User;

/**
 *
 * @author cck
 */
public interface IUserService {
	
	Integer login(String email, String pwd);
	Integer register(User user);
	Integer set(User user);
	
}
