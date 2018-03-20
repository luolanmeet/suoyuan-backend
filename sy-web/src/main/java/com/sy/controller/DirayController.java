package com.sy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.object.resp.BaseResp;
import com.sy.service.IDirayService;
import com.sy.util.RespUtil;

/**
 * 
 * @author cck
 */
@RestController
public class DirayController extends BaseController {
	
	@Reference
    private IDirayService dirayService;
	
	@Autowired
	private RespUtil respUtil;
	
	@RequestMapping(value = "/add")
	public BaseResp add(Integer userId, String context) {
		
		return success();
	}
	
	@RequestMapping(value = "/myDirays")
	public BaseResp diray(Integer userId) {
		
		return success(respUtil.getUserDiraysResq(dirayService.getByUserId(userId)));
	}
	
	@RequestMapping(value = "/getByWriteTime")
	public BaseResp diray(Integer userId, String dateTime) {
		
		return success(dirayService.getByWriteTime(userId, dateTime));
	}
	
}
