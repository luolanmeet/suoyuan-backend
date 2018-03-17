package com.sy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.cck.Diray;
import com.object.req.AddDirayReq;
import com.sy.mapper.DirayMapper;
import com.sy.service.IDirayService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cck
 */
@Slf4j
@Service
@Component
public class DirayService implements IDirayService {
	
	@Autowired
	private DirayMapper dirayMapper;
	
	@Override
	public void add(AddDirayReq req) {
		
		dirayMapper.save(req.getUserId(), req.getContent());
		log.info("save diray success");
	}

	@Override
	public List<Diray> getByUserId(Integer userId) {
		
		return dirayMapper.getByUserId(userId);
	}

	@Override
	public List<Diray> getByWriteTime(Integer userId, String dateTime) {
		
		return dirayMapper.getByWriteTime(userId, dateTime);
	} 
	
}
