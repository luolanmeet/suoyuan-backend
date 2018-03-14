package com.cck;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author cck
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String password;
	private String nickname;
	private String avator;
	private Integer isOpen;            // 0:不公开    1:公开
	private Integer dirayCount;
	
}
