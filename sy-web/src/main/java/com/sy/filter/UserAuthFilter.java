package com.sy.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.object.code.ErrorCode;
import com.object.exception.ErrorCodeException;
import com.sy.jwt.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cck
 */
@Slf4j
public class UserAuthFilter extends HandlerInterceptorAdapter {
	
	private final static Integer ZERO = 0;
	
	private final static String TOKEN = "token";
	private final static String USER_ID = "userId";
	
	private final static ErrorCodeException USER_NO_LOGIN 
		= new ErrorCodeException(ErrorCode.USER_NO_LOGIN, "用户未登录");
	
	private final static ErrorCodeException ERROR_TOKEN 
		= new ErrorCodeException(ErrorCode.ERROR_TOKEN, "token校验未通过");
	
	private final static Map<String,Integer> ignorePath = new HashMap<String, Integer>() {
		/**  */
		private static final long serialVersionUID = 1L;

		{
			put("/login", ZERO);
			put("/register", ZERO);
		}
	};
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) req;
		
		String path = request.getRequestURI();
		log.info("request path:{}", path);
		
		// 如果不是登录的请求，则需要校验是否登录
		if (ignorePath.containsKey(path)) {
			return true;
		}
		
//		String token = request.getHeader(TOKEN);
		String token = request.getParameter(TOKEN);
		String userId = request.getParameter(USER_ID);
		
		if (token == null) {
			throw USER_NO_LOGIN;
		}
		
		log.info("token : {}", token);
		
		Optional<DecodedJWT> checkToken = JwtUtil.checkToken(token);
		
		if (checkToken.isPresent() && userId != null) {
			
			String userIdInToken = String.valueOf(checkToken.get().getClaim(USER_ID).asInt());
			
			if (!userId.equals(userIdInToken)) {
				throw ERROR_TOKEN;
			}
		} else {
			
			throw ERROR_TOKEN;
		}
		
		return true;
	}
	
}
