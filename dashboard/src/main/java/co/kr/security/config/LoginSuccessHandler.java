package co.kr.security.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import co.kr.security.service.impl.SecurityMapper;

public class LoginSuccessHandler  implements AuthenticationSuccessHandler{

	@Resource(name="securityMapper")
	private SecurityMapper securityMapper;
	
	//이 클래스를 이용하여 화면 이동 (sendRedirect 메소드 사용 화면 이동)
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public String defaultUrl;
	
	public String getDefaultUrl(){
		return defaultUrl;
	}
	
	public void setDefaultUrl(String defaultUrl){
		this.defaultUrl = defaultUrl;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		redirectStrategy.sendRedirect(request, response, defaultUrl);
	}
	
	//실패 횟수 카운트 초기화
	public void resetCnt(HttpServletRequest request, HttpServletResponse response){
		String user_id = request.getParameter("user_id");
		//로그인 횟수 초기화
		securityMapper.resetCnt(user_id);
	}
	
}