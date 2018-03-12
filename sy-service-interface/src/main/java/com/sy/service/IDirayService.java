package com.sy.service;

import java.util.List;

import com.cck.Diray;

/**
 *
 * @author cck
 */
public interface IDirayService {

	int add(Diray diray);
	List<Diray> getByUserId(Integer userId);
	List<Diray> getByWriteTime(Integer userId, String dateTime);
}
