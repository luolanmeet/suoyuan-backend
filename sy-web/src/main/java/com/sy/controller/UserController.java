package com.sy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cck.Diray;
import com.cck.User;
import com.object.resp.BaseResp;
import com.object.resp.LoginResp;
import com.object.resp.UserMsgAndDiray;
import com.sy.jwt.JwtUtil;
import com.sy.service.IDirayService;
import com.sy.service.IUserService;
import com.sy.util.RespUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cck
 */
@Slf4j
@RestController
public class UserController extends BaseController {
	
	@Reference
    private IUserService userService;
	
	@Reference
	private IDirayService dirayService; 
	
	@Autowired
	private RespUtil util;
	
	@RequestMapping(value = "/register")
	public BaseResp register(String email, String pwd, String nickname) {
		
		userService.register(email, pwd, nickname);
		return success();
	}
	
	@RequestMapping(value = "/login")
	public BaseResp login(String email, String pwd) {
		
		User user = userService.login(email, pwd);
		String token = JwtUtil.createToken(user.getId());
		
		LoginResp resp = LoginResp.builder()
			.token(token)
			.userId(user.getId())
			.build();
		
		log.info("user[{}] login success", user.getId());
		return success(resp);
	}
	
	@RequestMapping(value = "/myIndex")
	public BaseResp userIndex(Integer userId) {
		
		User user = userService.getById(userId);
		List<Diray> dirays = 
				dirayService.getByWriteTime(userId, user.getLastDirayDate());
		
		UserMsgAndDiray resp = util.getUserIndexResp(user, dirays);
		return success(resp);
	}
	
	@RequestMapping(value = "/update")
	public BaseResp update(User user) {
		
		userService.update(user);
		return success(user);
	}
	
}
