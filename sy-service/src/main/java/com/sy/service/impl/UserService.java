package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.User;
import com.object.code.ErrorCode;
import com.object.exception.ErrorCodeException;
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
	public Integer login(String email, String pwd)  throws ErrorCodeException {
		System.out.println(email + " " + pwd);
		
		if (1 == 1) {
			throw new ErrorCodeException(ErrorCode.INTERNAL_ERROR, "我故意的");
		}
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
