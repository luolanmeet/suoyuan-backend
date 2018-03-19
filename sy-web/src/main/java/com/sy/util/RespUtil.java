package com.sy.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cck.Diray;
import com.cck.User;
import com.object.resp.DirayResp;
import com.object.resp.UserMsgAndDiray;

/**
 * 
 * @author cck
 */
@Component
public class RespUtil {
	
	public final static ThreadLocal<SimpleDateFormat> DATE_FORMATTER
		= ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy年MM月dd号 EEEE"));
	
	public final static ThreadLocal<SimpleDateFormat> TIME_FORMATTER
		= ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm"));
	
	public UserMsgAndDiray getUserIndexResp(User user, List<Diray> dirays) {
		
		String dateStr = "";
		List<DirayResp> dirayResps = new ArrayList<>();
		
		if (!dirays.isEmpty()) {
			Date date = dirays.get(0).getWriteTime();
			dateStr = DATE_FORMATTER.get().format(date);
			
			for (Diray diray : dirays) {
				DirayResp dirayResp = DirayResp.builder()
						.time(TIME_FORMATTER.get().format(diray.getWriteTime()))
						.content(diray.getContent())
						.build();
				dirayResps.add(dirayResp);
			}
		}
		
		UserMsgAndDiray resp = UserMsgAndDiray.builder()
				.id(user.getId())
				.email(user.getEmail())
				.nickname(user.getNickname())
				.avator(user.getAvator())
				.signature(user.getSignature())
				.dirayCount(user.getDirayCount())
				.lastDirayDate(dateStr)
				.dirays(dirayResps)
				.build();
		
		return resp;
	}
	
}
