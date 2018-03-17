package com.sy.service;

import java.util.List;

import com.cck.Diray;
import com.object.req.AddDirayReq;

/**
 *
 * @author cck
 */
public interface IDirayService {

	void add(AddDirayReq req);
	List<Diray> getByUserId(Integer userId);
	List<Diray> getByWriteTime(Integer userId, String dateTime);
}
