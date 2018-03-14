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
	public User login(String email, String pwd)  
			throws ErrorCodeException {
		
		User user = userMapper.getByEmailAndPwd(email, pwd);
		if (user == null) {
			throw new ErrorCodeException(ErrorCode.USER_NO_EXITS, "账户或密码错误");
		}
		log.info("{}", user);
		return user;
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
		int save = userMapper.save(user);
		
		if(save == 0) {
			throw new ErrorCodeException(ErrorCode.EMAIL_HAS_REGISTER, "邮箱已被注册");
		}
	}

	@Override
	public void set(User user) {
	}

}
