package com.sy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.object.resp.BaseResp;
import com.sy.service.IDirayService;

/**
 * 
 * @author cck
 */
@RestController
public class DirayController extends BaseController {
	
	@Reference
    private IDirayService dirayService;
	
	@RequestMapping(value = "/add")
	public BaseResp register(String email, String pwd, String nickname) {
		
		return success();
	}
	
	@RequestMapping(value = "/getByUserId")
	public BaseResp diray(Integer userId) {
		
		return success(dirayService.getByUserId(userId));
	}
	
	@RequestMapping(value = "/getByWriteTime")
	public BaseResp diray(Integer userId, String dateTime) {
		
		return success(dirayService.getByWriteTime(userId, dateTime));
	}
	
}
