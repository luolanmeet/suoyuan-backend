package com.sy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sy.service.IUserService;

/**
 * 
 * @author cck
 */
@RestController
public class UserController {
	
	@Reference
    private IUserService userService;
	
	@RequestMapping(value = "/")
	public String register() {
		userService.login("hello", "dubbo");
		return "hello world";
	}
	
}
