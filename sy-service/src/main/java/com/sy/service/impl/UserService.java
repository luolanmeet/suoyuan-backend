package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.User;
import com.object.code.ErrorCode;
import com.object.exception.ErrorCodeException;
import com.sy.mapper.UserMapper;
import com.sy.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cck
 */
@Slf4j
@Service
@Component
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void login(String email, String pwd)  
			throws ErrorCodeException {
		
		System.out.println(email + " " + pwd);
		throw new ErrorCodeException(ErrorCode.INTERNAL_ERROR, "我故意的");
	}

	@Override
	public void register(String email, String pwd, String nickname)  
			throws ErrorCodeException {
		
		log.info("req:register emial:{} pwd:{}");
		
		User user = User.builder()
		        .email(email)
		        .password(pwd)
		        .nickname(nickname)
		        .build();
		userMapper.save(user);
	}

	@Override
	public void set(User user) {
	}

}
