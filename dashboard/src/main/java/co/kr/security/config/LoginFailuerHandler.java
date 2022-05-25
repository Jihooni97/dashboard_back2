package co.kr.security.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import co.kr.security.service.impl.SecurityMapper;

public class LoginFailuerHandler implements AuthenticationFailureHandler {
	
	@Resource(name="securityMapper")
	private SecurityMapper securityMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if (exception instanceof UsernameNotFoundException){
			request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
			System.out.println("존재하지 않는 아이디");
		}else if(exception instanceof BadCredentialsException){
			request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
			System.out.println("아이디 또는 패스워드가 틀립니다.");
		}else if(exception instanceof LockedException){
			request.setAttribute("loginFailMsg", "잠긴 계정입니다.");
			System.out.println("잠긴 계정입니다.");
		}else if(exception instanceof DisabledException){
			request.setAttribute("loginFailMsg", "계정이 비활성화 되었습니다.");
			System.out.println("계정이 비활성화 되었습니다.");
		}else if(exception instanceof AuthenticationException){
			request.setAttribute("loginFailMsg", "만료된 계정입니다.");
			System.out.println("만료된 계정입니다.");
		}else if(exception instanceof CredentialsExpiredException){
			request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
			System.out.println("비밀번호가 만료되었습니다.");
		}
		
		loginCnt(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board/loginPage.jsp");
		dispatcher.forward(request, response);
	}
	
	public void loginCnt(HttpServletRequest request, HttpServletResponse response){
		String user_id = request.getParameter("user_id");
		int exists = securityMapper.exists(user_id);
		
		if(exists ==1){
			int tryCnt = securityMapper.selectCnt(user_id);
			if(tryCnt>=3){
				securityMapper.lock(user_id);
			}else{
				securityMapper.plusCnt(user_id);
				int tryCnt2 = securityMapper.selectCnt(user_id);
			}
		}
	}
	

}
