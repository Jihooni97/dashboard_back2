package co.kr.security.config;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import co.kr.security.service.impl.SecurityMapper;

public class Interceptor extends HandlerInterceptorAdapter {

	@Resource(name = "securityMapper")
	private SecurityMapper securityMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HashMap<String, Object>params = new HashMap<>();
		String URL = request.getRequestURI().toString();
		params.put("url", URL);
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String permisson = authentication.getAuthorities().toArray()[0].toString();
		
		
		
		return false;
		
	}

}
