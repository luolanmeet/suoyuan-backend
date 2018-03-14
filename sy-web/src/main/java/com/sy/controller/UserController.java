package com.sy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.object.resp.BaseResp;
import com.sy.service.IUserService;

/**
 * 
 * @author cck
 */
@RestController
public class UserController extends BaseController {
	
	@Reference
    private IUserService userService;
	
	@RequestMapping(value = "/register")
	public BaseResp register(String email, String pwd, String nickname) {
		
	    userService.register(email, pwd, nickname);
		return success();
	}
	
}
