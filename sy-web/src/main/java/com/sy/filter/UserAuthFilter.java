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
	
	private final static Map<String,Integer> ignorePath = new HashMap<String, Integer>() {
		/**  */
		private static final long serialVersionUID = 1L;

		{
			put("/login", ZERO);
			put("/register", ZERO);
		}
	};
	
	private final static ErrorCodeException USER_NO_LOGIN 
		= new ErrorCodeException(ErrorCode.USER_NO_LOGIN, "用户未登录");
	
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
		
		String token = request.getHeader("token");
		
		if (token == null) {
			throw USER_NO_LOGIN;
		}
		
		log.info("token : {}", token);
		
		Optional<DecodedJWT> checkToken = JwtUtil.checkToken(token);
		
		if (checkToken.isPresent()) {
			
			checkToken.get();
		} else {
			throw new ErrorCodeException(ErrorCode.USER_NO_EXITS, "账户或密码错误");
		}
		
		return true;
	}
	
}
