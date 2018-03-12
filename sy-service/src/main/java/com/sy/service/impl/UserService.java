package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.User;
import com.sy.mapper.UserMapper;
import com.sy.service.IUserService;

/**
 *
 * @author cck
 */
@Service
@Component
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Integer login(String email, String pwd) {
		System.out.println(email + " " + pwd);
		System.out.println(userMapper.isExistEmail(email));
		return null;
	}

	@Override
	public Integer register(User user) {
		return null;
	}

	@Override
	public Integer set(User user) {
		return null;
	}

}
