package com.object.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 首页 用户以往的日记
 * @author cck
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDirayResp {
	
	String month;
	String day;
	String time;
	String content;
}
