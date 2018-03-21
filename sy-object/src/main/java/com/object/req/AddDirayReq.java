package com.object.req;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author cck
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddDirayReq implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String content;
}
